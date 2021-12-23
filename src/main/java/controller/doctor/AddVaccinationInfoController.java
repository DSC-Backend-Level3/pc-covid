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
        date = Helper.convertDate(request.getParameter("date"));

        VaccinationInfoDTO vaccinationInfo = vaccinationInfoDao.getTheLatestVaccinationInfoByIdUser(residentID);
        VaccineDTO vaccine = vaccineDao.getVaccineByID(vaccineID);
        if (vaccinationInfo != null) {
            boolean isValidDate = (Validator.isValidInterval(vaccinationInfo.getDate(), date, vaccine.getInterval()))
                                && (Validator.isBeforeCurrentDate(date));
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
        String errorMessage;
        try {
            String link = request.getParameter("btAction");
            if (postHandler(request, response) && link.equals("Add Vaccination")) {
                response.sendRedirect(PathValue.HOME_PAGE);
            } else {
                request.setAttribute("dateErrorMessage", "Date is not suitable for the next injection!");
                request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_INVALID_FORM).forward(request,response);
            }
        } catch (UnsupportedEncodingException | NamingException ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        } catch (DateTimeException ex) {
            errorMessage = ex.getMessage();
            if (ex.getMessage().contains("could not be parsed")) {
                errorMessage = "Date is invalid!";
                request.setAttribute("dateErrorMessage", errorMessage);
                request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM).forward(request, response);
            } else {
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        } catch (NumberFormatException ex) {
            errorMessage = ex.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } catch (SQLException ex) {
            log(ex.getMessage());
            errorMessage = ex.getMessage();
            if (ex.getMessage().contains("FOREIGN KEY")) {
                errorMessage = "ID is not available!";
                request.setAttribute("notExistedError", errorMessage);
                request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_INVALID_FORM).forward(request, response);
            } else {
                if (ex.getMessage().contains("PRIMARY KEY")) {
                    errorMessage = "ID is available!";
                    request.setAttribute("ExistedError", errorMessage);
                    request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_INVALID_FORM).forward(request, response);
                } else {
                    if (errorMessage.contains("out-of-range value")) {
                        System.out.println("problem is here");
                        errorMessage = "Date is invalid!";
                        request.setAttribute("dateErrorMessage", errorMessage);
                        request.getRequestDispatcher(Router.PAGE.VACCINATION_INFO_FORM).forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", errorMessage);
                        request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
                    }
                }
            }
        }
    }
}
