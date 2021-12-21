package controller.common;

import constant.Attribute;
import constant.PathValue;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //do login
            login(request, response);
            //on success
            response.sendRedirect(PathValue.HOME_PAGE + "?result=success");
        } catch (IllegalArgumentException ex) { //on fail
            //forward back to login page
            request.getRequestDispatcher(Router.PAGE.LOGIN_PAGE).forward(request, response);
        } catch (SQLException | NamingException | NoSuchAlgorithmException ex) { //internal error
            //set error message
            request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, ex.getMessage());
            //forward to error page
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }


    private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        //initialize resource
        ResidentDao residentDao = new ResidentDaoImpl();
        //get parameter
        String idNumber = request.getParameter(Attribute.USER.USER_ID);
        String password = request.getParameter(Attribute.USER.USER_PASSWORD);
        String hashedPassword = Helper.hashString(password);
        //if parameter is missing
        if (idNumber == null || password == null) {
            request.setAttribute(Attribute.ERROR.MISSING_PARAMETER, "ID number or password can not be empty");
            throw new IllegalArgumentException();
        }
        //get user
        ResidentDTO residentDTO = residentDao.getResidentById(idNumber);
        //check if user is exist
        if (residentDTO == null) {
            request.setAttribute(Attribute.ERROR.OBJECT_NOT_FOUND, Attribute.ERROR_MESSAGE.OBJECT_NOT_FOUND("User"));
            throw new IllegalArgumentException();
        }
        //hashing password
        //password = hashingMethod(password);
        //comparing password
        if (!hashedPassword.equalsIgnoreCase(residentDTO.getPassword())) {
            request.setAttribute("wrongPasswordError", "Wrong password");
            throw new IllegalArgumentException();
        }
        //on success
        //get session
        HttpSession session = request.getSession();
        //set user attribute to session
        session.setAttribute(Attribute.USER.USER_ID, residentDTO.getId());
        session.setAttribute(Attribute.USER.ROLE, residentDTO.getRoleID());
        session.setAttribute(Attribute.USER.USER_NAME, residentDTO.getFirstName() + residentDTO.getLastName());
    }
}
