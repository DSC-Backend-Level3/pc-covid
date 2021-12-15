package controller.common;

import constant.Router;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/LogoutController")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get current session
        HttpSession session = request.getSession();
        //remove exist attribute
        session.removeAttribute("role");
        //invalidate session
        session.invalidate();
        //forward to login page
        request.getRequestDispatcher(Router.LOGIN_PAGE).forward(request,response);
    }
}
