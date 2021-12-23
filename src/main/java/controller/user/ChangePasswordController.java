package controller.user;

import constant.Attribute;
import dao.implement.ResidentDaoImpl;
import utils.GetParam;
import utils.Helper;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static constant.Router.*;
import static constant.Router.PAGE.ERROR_PAGE;
import static constant.Router.PAGE.UPDATE_PASSWORD_SUCCESS;

@WebServlet(name = "ChangePasswordController", value = "/ChangePasswordController")
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        String oldPassword = request.getParameter(Attribute.USER.USER_PASSWORD);
        String newPassword = request.getParameter(Attribute.USER.NEW_PASSWORD);
        String newPasswordConfirm = request.getParameter(Attribute.USER.CONFIRM_PASSWORD);
        request.setAttribute("OLD_PASSWORD", oldPassword);
        request.setAttribute("NEW_PASSWORD", newPassword);
        String checkValid = "Updated Successfully";
        String url = ERROR_PAGE;
        try {
            if(session != null) {
                String id = (String) session.getAttribute(Attribute.USER.USER_ID);
                String hashedOldPassword = Helper.hashString(oldPassword);
                String hashedNewPassword = Helper.hashString(newPassword);
                String hashedNewPasswordConfirm = Helper.hashString(newPasswordConfirm);
                ResidentDaoImpl dao = new ResidentDaoImpl();
                boolean check = dao.checkPassword(id, hashedOldPassword);
                if (check) {
                    if (hashedNewPassword.equalsIgnoreCase(hashedNewPasswordConfirm)) {
                        dao.updateResidentPassword(id, hashedNewPassword);
                    }else{
                        String checkInValid = "Updated Failed. Password does not match.";
                        request.setAttribute("CHECK_INVALUD", checkInValid);
                    }
                }else{
                    String checkInValid = "Updated Failed. Enter the wrong password";
                    request.setAttribute("CHECK_INVALUD", checkInValid);
                }
                request.setAttribute("CHECK_VALID", checkValid);

                url = UPDATE_PASSWORD_SUCCESS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }
}
