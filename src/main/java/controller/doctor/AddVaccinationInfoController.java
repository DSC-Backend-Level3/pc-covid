package controller.doctor;

import com.google.gson.Gson;
import constant.Router;
import dao.*;
import dao.implement.*;
import dto.*;
import utils.Helper;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;
import java.util.List;

import static constant.Router.PAGE.VACCINATION_INFO_FORM;
import static constant.Router.USER.VIEW_PROFILE_CONTROLLER;

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

    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, SQLException, NamingException, DateTimeParseException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        VaccinationInfoDao vaccinationInfoDao = new VaccinationInfoDaoImpl();

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

        Timestamp injectionDate = Helper.convertDate(date);

        VaccinationInfoDTO vaccinationInfo= new VaccinationInfoDTO(id, residentID, vaccineID, wardID, injectionDate);
        return vaccinationInfoDao.addNewVaccinationInfo(vaccinationInfo);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String link = request.getParameter("btAction");
            if (getHandler(request, response) && link.equals("Add Vaccination")) {
                request.getRequestDispatcher(VACCINATION_INFO_FORM).forward(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DateTimeParseException {
        try {
            if (postHandler(request, response)) {
                response.sendRedirect("homepage?result=success");
            }

        } catch (DateTimeParseException e){
            request.setAttribute("ERROR", "Invalid date.");
            String url = VACCINATION_INFO_FORM + "?btAction=Add Vaccination";
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch (Exception ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }
}
