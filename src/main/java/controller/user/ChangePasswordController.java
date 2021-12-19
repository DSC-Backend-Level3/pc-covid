package controller.user;

import dao.implement.ResidentDaoImpl;
import utils.GetParam;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static constant.Router.*;

@WebServlet(name = "ChangePasswordController", value = "/ChangePasswordController")
public class ChangePasswordController extends HttpServlet {
    private String hashString(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("txtID");
        String oldPassword = request.getParameter("txtPassword");
        String newPassword = request.getParameter("txtNewPassword");
        String newPasswordConfirm = request.getParameter("txtNewPasswordConfirm");
        String button = request.getParameter("btAction");
        String url = ERROR_PAGE;
        try {
            if(button.equals("Save Changes")) {
                String hashedOldPassword = hashString(oldPassword);
                String hashedNewPassword = hashString(newPassword);
                String hashedNewPasswordConfirm = hashString(newPasswordConfirm);
                ResidentDaoImpl dao = new ResidentDaoImpl();
                boolean check = dao.checkPassword(id, hashedOldPassword);
                if (check) {
                    if (hashedNewPassword.equals(hashedNewPasswordConfirm)) {
                        dao.updateResidentPassword(id, hashedNewPassword);
                        url = UPDATE_PASSWORD;
                    }
                }
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
