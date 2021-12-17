package controller.dispatcher;

import constant.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RequestDispatcher", value = "/*")
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
        int role = (int) session.getAttribute("role");
        switch (role) {
            case 0 :
                request.getRequestDispatcher(Router.GUEST_DISPATCHER).forward(request,response);
                break;
            case 1:
                request.getRequestDispatcher(Router.ADMIN_DISPATCHER).forward(request, response);
                break;
            case 2:
                request.getRequestDispatcher(Router.DOCTOR_DISPATCHER).forward(request, response);
                break;
            case 3:
                request.getRequestDispatcher(Router.USER_DISPATCHER).forward(request, response);
                break;
        }
    }
}
