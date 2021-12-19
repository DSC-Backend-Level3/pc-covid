package controller.dispatcher;

import constant.Attribute;
import constant.Role;
import constant.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RequestDispatcher", value = "/RequestDispatcher")
public class RequestDispatcher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int role = (int) session.getAttribute(Attribute.USER.ROLE);
        switch (role) {
            case Role.GUEST :
                request.getRequestDispatcher(Router.DISPATCHER.GUEST_DISPATCHER).forward(request,response);
                break;
            case Role.ADMIN:
                request.getRequestDispatcher(Router.DISPATCHER.ADMIN_DISPATCHER).forward(request, response);
                break;
            case Role.DOCTOR:
                request.getRequestDispatcher(Router.DISPATCHER.DOCTOR_DISPATCHER).forward(request, response);
                break;
            case Role.USER:
                request.getRequestDispatcher(Router.DISPATCHER.USER_DISPATCHER).forward(request, response);
                break;
        }
    }
}
