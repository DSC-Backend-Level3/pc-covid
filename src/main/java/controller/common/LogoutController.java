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
        //get current session
        HttpSession session = request.getSession(false);
        //remove exist attribute
        if(session != null){
            //invalidate session
            session.invalidate();

        }

        response.sendRedirect("/pc_covid");
    }
}
