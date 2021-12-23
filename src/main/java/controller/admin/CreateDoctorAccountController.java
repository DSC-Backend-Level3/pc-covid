package controller.admin;

import constant.Router;
import dao.ResidentDao;
import dao.implement.ResidentDaoImpl;
import dto.ResidentDTO;
import utils.Helper;
import utils.Validator;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;

import static constant.Router.PAGE.DOCTOR_ACCOUNT_FORM;
import static constant.Router.PAGE.ERROR_PAGE;

@WebServlet(name = "CreateDoctorAccountController", value = "/CreateDoctorAccountController")
public class CreateDoctorAccountController extends HttpServlet {
    private final String PAGE_RETURN = "viewDoctor?btAction=viewDoctor";
    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, SQLException, NamingException, DateTimeParseException, NoSuchAlgorithmException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ResidentDao residentDao = new ResidentDaoImpl();

        String id;
        String firstName;
        String lastName;
        String phoneNumber;
        String email;
        String healthInsuranceID;
        String gender;
        String DOB;
        String nationality;
        int wardID;
        String houseNumber;
        String password;
        String confirmPassword;

        id = request.getParameter("id");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        phoneNumber = request.getParameter("phoneNumber");
        healthInsuranceID = request.getParameter("healthInsuranceID");
        DOB = request.getParameter("DOB");
        nationality = request.getParameter("nationality");
        wardID = Integer.parseInt(request.getParameter("wardID"));
        houseNumber = request.getParameter("houseNumber");
        password = Helper.hashString(request.getParameter("password"));
        confirmPassword = Helper.hashString(request.getParameter("confirmPassword"));
        email = request.getParameter("email");
        gender = request.getParameter("gender");

        Timestamp date = Helper.convertDate(DOB);
        if (Validator.isBeforeCurrentDate(date) == false) {
            request.setAttribute("dateErrorMessage", "Date of birth can not over today!");
            return false;
        }
        System.out.println("date is valid");
        if (confirmPassword.equals(password) == false) {
            request.setAttribute("passwordError", "Password must be the same!");
            return false;
        }
        ResidentDTO residentDTO = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, date, nationality, wardID, houseNumber, 3, password);

        return residentDao.addNewResident(residentDTO);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage;
        try {
            if (postHandler(request, response) == false) {
                request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM).forward(request, response);
            } else {
                response.sendRedirect(PAGE_RETURN);
            }
        }catch (SQLException ex) {
            errorMessage = ex.getMessage();
            System.out.println(errorMessage);
            if (errorMessage.contains("PRIMARY KEY")) {
                errorMessage = "ID is duplicated!";
                request.setAttribute("ExistedError", errorMessage);
                request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM).forward(request, response);
            } else {
                if (errorMessage.contains("out-of-range value")) {
                    System.out.println("problem is here");
                    errorMessage = "Date is invalid!";
                    request.setAttribute("dateErrorMessage", errorMessage);
                    request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM).forward(request, response);
                } else {
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
                }
            }
        } catch (NamingException | NoSuchAlgorithmException | NumberFormatException ex) {
            errorMessage = ex.getMessage();
            System.out.println(errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } catch (DateTimeParseException ex) {
            errorMessage = ex.getMessage();
            if (ex.getMessage().contains("could not be parsed")) {
                errorMessage = "Date is invalid!";
                request.setAttribute("dateErrorMessage", errorMessage);
                request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM).forward(request, response);
            } else {
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            }
        }
    }
}
