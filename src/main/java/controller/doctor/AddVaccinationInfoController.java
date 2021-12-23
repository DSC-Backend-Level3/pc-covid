package controller.doctor;

import com.google.gson.Gson;
import constant.PathValue;
import constant.Router;
import dao.*;
import dao.implement.*;
import dto.*;
import utils.Helper;
import utils.Validator;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import static constant.Router.PAGE.DOCTOR_ACCOUNT_FORM;
import static constant.Router.PAGE.ERROR_PAGE;

@WebServlet(name = "AddVaccinationInfoController", value = "/AddVaccinationInfoController")
public class AddVaccinationInfoController extends HttpServlet {

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
        WardDao wardDao = new WardDaoImpl();
        DistrictDao districtDao = new DistrictDaoImpl();
        ProvinceDao provinceDao = new ProvinceDaoImpl();

        String residentID;
        int id;
        int vaccineID;
        int wardID;
        Timestamp date;

        residentID = request.getParameter("residentID");
        id = Integer.parseInt(request.getParameter("id"));
        vaccineID = Integer.parseInt(request.getParameter("vaccineID"));
        wardID = Integer.parseInt(request.getParameter("wardID"));

        int districtID = Integer.parseInt(request.getParameter("districtID"));
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));

        request.setAttribute("ward", wardDao.getWardByID(wardID));
        request.setAttribute("district", districtDao.getDistrictByID(districtID));
        request.setAttribute("province", provinceDao.getProvinceByID(provinceID));
        request.setAttribute("vaccine", vaccineDao.getVaccineByID(vaccineID));
        VaccineDTO vaccine = vaccineDao.getVaccineByID(vaccineID);
        request.setAttribute("vaccineName", vaccine.getName());
        request.setAttribute("vaccineID", vaccine.getId());
        date = Helper.convertDate(request.getParameter("date"));

        VaccinationInfoDTO vaccinationInfo = vaccinationInfoDao.getTheLatestVaccinationInfoByIdUser(residentID);

        if (vaccinationInfo != null) {
            boolean isValidDate = Validator.isValidInterval(vaccinationInfo.getDate(), date, vaccine.getInterval());
            if (isValidDate == false) {
                return false;
            }
        }
        VaccinationInfoDTO result = new VaccinationInfoDTO(id, residentID, vaccineID, wardID, date);
        return vaccinationInfoDao.addNewVaccinationInfo(result);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (getHandler(request, response)) {
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
            if (postHandler(request, response)) {
                response.sendRedirect(PathValue.HOME_PAGE);
            } else {
                request.setAttribute("dateError", "Date is not suitable for the next injection!");
                doGet(request, response);
            }
        } catch (UnsupportedEncodingException | NamingException ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        } catch (DateTimeException ex) {
            request.setAttribute("dateError", "Date is invalid!");
            request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_FORM).forward(request,response);
        } catch (SQLException ex) {
            log(ex.getMessage());
            String errorMessage = ex.getMessage();
            if (ex.getMessage().contains("FOREIGN KEY")) {
                errorMessage = "ID is not available!";
                request.setAttribute("notExistedError", errorMessage);
                request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_FORM).forward(request, response);
            } else {
                if (ex.getMessage().contains("PRIMARY KEY")) {
                    errorMessage = "ID is available!";
                    request.setAttribute("existedError", errorMessage);
                    request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_FORM).forward(request, response);
                } else {
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
                }
            }

        }
    }
}
