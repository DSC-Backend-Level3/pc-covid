package controller.admin;

import dao.ResidentDao;
import dao.implement.ResidentDaoImpl;
import dto.ResidentDTO;
import utils.Helper;
import utils.Validator;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private final String PAGE_RETURN = "viewDoctor";

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

        if (Validator.isValidNumberString(id, "[0-9]{12}") == false) {
            request.setAttribute("IDError", "ID must have 12-number length!");
            throw new IllegalArgumentException();
        }

        if (residentDao.getResidentById(id) != null) {
            request.setAttribute("existedError", "ID is duplicated!!");
            throw new IllegalArgumentException();
        }
        if (Validator.isValidAge(date) == false) {
            request.setAttribute("dateError", "Your age is not suitable!");
            throw new IllegalArgumentException();
        }
        if (confirmPassword.equals(password) == false) {
            request.setAttribute("passwordError", "Password must be the same!");
            throw new IllegalArgumentException();
        }

        if (Validator.isValidGmail(email) == false) {
            request.setAttribute("emailError", "Email is invalid!");
            throw new IllegalArgumentException();
        }

        if (Validator.isValidNumberString(phoneNumber, "[0-9]{10}") == false) {
            request.setAttribute("phoneError", "Phone number must have 10-number length!");
            throw new IllegalArgumentException();
        }
        if (Validator.isValidNumberString(healthInsuranceID, "[A-Z|a-z]{2}[0-9]{13}") == false) {
            request.setAttribute("healthIDError", "Health Insurance ID must have 15-character length!");
            throw new IllegalArgumentException();
        }
        if (houseNumber.length() > 40) {
            throw new IllegalArgumentException("House number invalid!");
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
                response.sendRedirect(PAGE_RETURN + "?create=success");
            }
        } catch (SQLException | NamingException | NoSuchAlgorithmException | NumberFormatException ex) {
            errorMessage = ex.getMessage();
            System.out.println(errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } catch (DateTimeParseException ex) {
            request.setAttribute("dateError", "Date is invalid!");
            request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM).forward(request, response);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM).forward(request, response);
        }
    }
}