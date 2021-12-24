package controller.common;

import constant.Attribute;
import constant.Router;
import dao.ResidentDao;
import dao.implement.ResidentDaoImpl;
import dto.ResidentDTO;
import utils.Helper;

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
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;


/**
 * This controller handle create account request from user
 */
@WebServlet(name = "CreateAccountController", value = "/CreateAccountController")
public class CreateAccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Router.PAGE.REGISTER_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            createAccount(request, response);
            response.sendRedirect(Router.PAGE.LOGIN_PAGE + "?create=success");
        } catch (IllegalArgumentException e) {
            request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, e.getMessage());
            doGet(request, response);
        } catch (NamingException | SQLException | NoSuchAlgorithmException e) {
            log(e.getMessage());
            request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, e.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        response.setHeader("Cache-Control", "no-cache, no-store");
        request.setCharacterEncoding("UTF-8");
        //initialize resources
        ResidentDao residentDao = new ResidentDaoImpl();

        String _firstName, // user first name
                _lastName, // user last name
                _id, // user id number
                _password, // user password
                _confirmPassword, // user confirm password
                _dob; //user date of birth


        //get parameter
        _firstName = request.getParameter(Attribute.USER.FIRST_NAME);
        _lastName = request.getParameter(Attribute.USER.LAST_NAME);
        _id = request.getParameter(Attribute.USER.USER_ID);
        _password = request.getParameter(Attribute.USER.USER_PASSWORD);
        _confirmPassword = request.getParameter(Attribute.USER.CONFIRM_PASSWORD);
        _dob = request.getParameter(Attribute.USER.DOB);
        //validate variable
        if (_id == null || _password == null || _confirmPassword == null ||
                _firstName == null || _lastName == null || _dob == null) {
            throw new IllegalArgumentException("Missing parameter");
        }

        if (_id.length() != 12) {
            throw new IllegalArgumentException("Id number length must equal 12");
        }

        if (!_password.equals(_confirmPassword)) {
            throw new IllegalArgumentException("Confirm password must be the same with password");
        }

        //convert dob from String type to Timestamp type
        Timestamp dob = Helper.convertDate(_dob);
        //get date in past
        Timestamp previous = Timestamp.valueOf("1900-01-01 00:00:00");
        //get current date
        Timestamp instant = Timestamp.from(Instant.now());
        //validate dob
        if(dob.before(previous) || dob.after(instant)){
            throw new IllegalArgumentException("Invalid date of birth");
        }

        if (residentDao.getResidentById(_id) != null) {
            throw new IllegalArgumentException("This user is already exist!");
        }
        //add new account to database
        String hashedPassword = Helper.hashString(_password);

        if (!residentDao.addNewResident(new ResidentDTO(_id, _firstName, _lastName, null, null, null, "M", dob, "Việt Nam", 1, null, 2, hashedPassword))) {
            throw new SQLException("Internal exception");
        }
        //return to login page with success notification
    }
}
