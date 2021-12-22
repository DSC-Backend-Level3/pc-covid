package controller.common;

import constant.Attribute;
import dao.ResidentDao;
import dao.implement.ResidentDaoImpl;
import dto.ResidentDTO;
import utils.Helper;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 * This controller handle create account request from user
 */
@WebServlet(name = "CreateAccountController", value = "/CreateAccountController")
public class CreateAccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, NoSuchAlgorithmException {
        //initialize resources
        ResidentDao residentDao = new ResidentDaoImpl();
        //get parameter
        String _firstName = request.getParameter(Attribute.USER.FIRST_NAME);
        String _lastName = request.getParameter(Attribute.USER.LAST_NAME);
        String _id  = request.getParameter(Attribute.USER.USER_ID);
        String _password = request.getParameter(Attribute.USER.USER_PASSWORD);
        String _confirmPassword = request.getParameter(Attribute.USER.CONFIRM_PASSWORD);
        //validate variable
        if(_id == null || _password == null || _confirmPassword == null || _firstName == null || _lastName == null){
            throw new IllegalArgumentException("Missing parameter");
        }

        if(_id.length() != 12){
            throw new IllegalArgumentException("Id number length must equal 12");
        }

        if(!_password.equals(_confirmPassword)){
            throw new IllegalArgumentException("Confirm password must be the same with password");
        }

        if (residentDao.getResidentById(_id) != null){
            throw new IllegalArgumentException("This user is already exist!");
        }
        //add new account to database
        String hashedPassword = Helper.hashString(_password);

        if(residentDao.addNewResident(new ResidentDTO(_id, _firstName, _lastName, null, null, null, "M", "1900-01-01", "Việt Nam", 0, null, 3, hashedPassword ))){

        }
        //return to login page with success notification
    }
}
