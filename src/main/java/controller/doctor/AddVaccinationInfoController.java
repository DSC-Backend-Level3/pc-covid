package controller.doctor;

import com.google.gson.Gson;
import constant.Router;
import dao.*;
import dao.implement.*;
import dto.*;
import utils.Validator;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "AddVaccinationInfoController", value = "/AddVaccinationInfoController")
public class AddVaccinationInfoController extends HttpServlet {
    private static final String PAGE_RETURN = "homepage?result=success";

    protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        VaccineDao vaccineDao = new VaccineDaoImpl();

        //get vaccine list
        List<VaccineDTO> vaccineList = vaccineDao.getAllVaccines();
        request.setAttribute("vaccineList", vaccineList);

        response.setContentType("text/html;charset=UTF-8");
        return true;
    }

    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, SQLException, NamingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        VaccinationInfoDao vaccinationInfoDao = new VaccinationInfoDaoImpl();
        VaccineDao vaccineDao = new VaccineDaoImpl();
        boolean result = false;

        String residentID;
        int id;
        int vaccineID;
        int wardID;
        String date;

        residentID = request.getParameter("residentID");
        id = Integer.parseInt(request.getParameter("id"));
        vaccineID = Integer.parseInt(request.getParameter("vaccineID"));
        wardID = Integer.parseInt(request.getParameter("wardID"));
        date = request.getParameter("date");

        int districtID = Integer.parseInt(request.getParameter("districtID"));
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));
        WardDao wardDao = new WardDaoImpl();
        request.setAttribute("ward", wardDao.getWardByID(wardID));
        System.out.println(wardDao.getWardByID(wardID).getName());
        DistrictDao districtDao = new DistrictDaoImpl();
        request.setAttribute("district", districtDao.getDistrictByID(districtID));
        System.out.println(districtDao.getDistrictByID(districtID).getName());
        ProvinceDao provinceDao = new ProvinceDaoImpl();
        request.setAttribute("province", provinceDao.getProvinceByID(provinceID));
        System.out.println(provinceDao.getProvinceByID(provinceID));
        request.setAttribute("vaccine", vaccineDao.getVaccineByID(vaccineID));

        VaccinationInfoDTO vaccinationInfo = vaccinationInfoDao.getTheLatestVaccinationInfoByIdUser(residentID);
        VaccineDTO vaccine = vaccineDao.getVaccineByID(vaccineID);
        if (vaccinationInfo != null) {
            return Validator.checkTwoDate(vaccinationInfo.getDate(), date, vaccine.getInterval());
        }

        return vaccinationInfoDao.addNewVaccinationInfo(new VaccinationInfoDTO(id, residentID, vaccineID, wardID, date));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String link = request.getParameter("btAction");
            if (getHandler(request, response) && link.equals("Add Vaccination")) {
                request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_FORM).forward(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String link = request.getParameter("btAction");
            if (postHandler(request, response) && link.equals("Add Vaccination")) {
                response.sendRedirect("homepage?result=success");
//                request.getRequestDispatcher("homepage?result=success").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Date is not suitable for the next injection!");
                request.getRequestDispatcher("addVaccinationInfoInvalid.jsp").forward(request,response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }
}
