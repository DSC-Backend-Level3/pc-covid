package controller.doctor;

import com.google.gson.Gson;
import constant.Router;
import dao.*;
import dao.implement.*;
import dto.*;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static constant.Router.PAGE.DOCTOR_ACCOUNT_FORM;
import static constant.Router.PAGE.ERROR_PAGE;

@WebServlet(name = "AddVaccinationInfoController", value = "/AddVaccinationInfoController")
public class AddVaccinationInfoController extends HttpServlet {

    protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        response.setContentType("text/html");
        VaccineDao vaccineDao = new VaccineDaoImpl();

        //get vaccine list
        List<VaccineDTO> vaccineList = vaccineDao.getAllVaccines();
        request.setAttribute("vaccineList", vaccineList);

        return true;
    }

    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, SQLException, NamingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        VaccinationInfoDao vaccinationInfoDao = new VaccinationInfoDaoImpl();

        String residentID;
        int id;
        int vaccineID;
        int wardID;
        Timestamp date;

        residentID = request.getParameter("residentID");
        id = Integer.parseInt(request.getParameter("vaccineID"));
        vaccineID = Integer.parseInt(request.getParameter("vaccineID"));
        wardID = Integer.parseInt(request.getParameter("wardID"));
        date = Timestamp.valueOf(request.getParameter("date"));

        VaccinationInfoDTO vaccinationInfo= new VaccinationInfoDTO(id, residentID, vaccineID, wardID, date);

        return vaccinationInfoDao.addNewVaccinationInfo(vaccinationInfo);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (getHandler(request, response)) {
                request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM).forward(request, response);
            }
//            else {
//                request.getRequestDispatcher(Routers.EVENT_MANAGEMENT_CONTROLLER).forward(request, response);
//            }
        } catch (Exception ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (postHandler(request, response)) {
                request.getRequestDispatcher(Router.DOCTOR.VIEW_VACCINATION_CONTROLLER).forward(request, response);
            }
//            else {
//                request.getRequestDispatcher(Routers.EVENT_MANAGEMENT_CONTROLLER).forward(request, response);
//            }
        } catch (Exception ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
