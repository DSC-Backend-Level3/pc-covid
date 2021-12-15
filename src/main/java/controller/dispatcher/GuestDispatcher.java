package controller.dispatcher;

import constant.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GuestDispatcher", value = "/GuestDispatcher")
public class GuestDispatcher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "login":
                request.getRequestDispatcher(Router.LOGIN_CONTROLLER).forward(request,response);
                break;
            case "logout":
                request.getRequestDispatcher(Router.LOGOUT_CONTROLLER).forward(request,response);
                break;
            case "create":
                request.getRequestDispatcher(Router.CREATE_ACCOUNT_CONTROLLER).forward(request,response);
                break;
            default:
                request.setAttribute("message","Please login first!");
                request.getRequestDispatcher(Router.LOGIN_PAGE).forward(request,response);
        }
    }
}
