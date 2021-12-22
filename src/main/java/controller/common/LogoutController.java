package controller.common;

import constant.Attribute;
import constant.Router;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/LogoutController")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control","no-cache, no-store");
        //get current session
        HttpSession session = request.getSession();
        //remove exist attribute
        session.removeAttribute(Attribute.USER.USER_ID);
        session.removeAttribute(Attribute.USER.ROLE);
        session.removeAttribute(Attribute.USER.USER_NAME);
        //invalidate session
        session.invalidate();
        //forward to login page
        response.sendRedirect(Router.PAGE.LOGIN_PAGE);
    }
}
