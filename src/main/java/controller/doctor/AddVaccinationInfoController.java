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
        ResidentDao residentDao = new ResidentDaoImpl();
        VaccineDao vaccineDao = new VaccineDaoImpl();
        WardDao wardDao = new WardDaoImpl();
        DistrictDao districtDao = new DistrictDaoImpl();
        ProvinceDao provinceDao = new ProvinceDaoImpl();

        String residentID;
        int id;
        int vaccineID;
        int wardID;
        Timestamp date;
        boolean isValidDate;

        residentID = request.getParameter("residentID");
        id = Integer.parseInt(request.getParameter("id"));
        vaccineID = Integer.parseInt(request.getParameter("vaccineID"));
        wardID = Integer.parseInt(request.getParameter("wardID"));

        VaccinationInfoDTO latestVaccinationInfo = vaccinationInfoDao.getTheLatestVaccinationInfoByIdUser(residentID);
        VaccinationInfoDTO vaccinationInfo = vaccinationInfoDao.getVaccinationInfoByID(id);
        ResidentDTO resident = residentDao.getResidentById(residentID);
        request.setAttribute("vaccine", vaccineDao.getVaccineByID(vaccineID));
        date = Helper.convertDate(request.getParameter("date"));

        if (Validator.isValidNumberString(residentID, "[0-9]{12}") == false) {
            request.setAttribute("IDError", "ID must have 12-number length!");
            throw new IllegalArgumentException();
        }

        if (resident == null) {
            request.setAttribute("notExistedError", "ID is not available!");
            throw new IllegalArgumentException();
        }
        if (vaccinationInfo != null) {
            request.setAttribute("existedError", "ID is available");
            throw new IllegalArgumentException();
        }
        if (latestVaccinationInfo != null) {
            VaccineDTO vaccine = vaccineDao.getVaccineByID(latestVaccinationInfo.getVaccineID());
            isValidDate = Validator.isValidInterval(latestVaccinationInfo.getDate(), date, vaccine.getInterval())
                    && Validator.isBeforeCurrentDate(date);

        } else {
            isValidDate = Validator.isBeforeCurrentDate(date) && Validator.isValidFirstInjection(date);
        }

        if (isValidDate == false) {
            request.setAttribute("dateError", "Date is not suitable for the next injection!");
            throw new IllegalArgumentException();
        }

        VaccinationInfoDTO result = new VaccinationInfoDTO(id, residentID, vaccineID, wardID, date);
        return vaccinationInfoDao.addNewVaccinationInfo(result);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (getHandler(request, response)) {
                request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_FORM).forward(request, response);
            } else {
                request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
            }
        } catch (SQLException ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (postHandler(request, response)) {
                response.sendRedirect(PathValue.HOME_PAGE + "?add=success");
            } else {
                doGet(request, response);
            }
        } catch (UnsupportedEncodingException | NamingException | SQLException ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        } catch (DateTimeException ex) {
            request.setAttribute("dateError", "Date is invalid!");
            doGet(request, response);
        } catch (NumberFormatException ex) {
            request.setAttribute("numberError", "You should enter a number string!");
            doGet(request, response);
        } catch (IllegalArgumentException ex) {
            doGet(request, response);
        }
    }
}
