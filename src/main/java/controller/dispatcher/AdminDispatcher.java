package controller.dispatcher;

import constant.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminDispatcher", value = "/AdminDispatcher")
public class AdminDispatcher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path){
            case "add":
                request.getRequestDispatcher(Router.ADD_VACCINE_CONTROLLER).forward(request,response);
                break;
            case "create":
                request.getRequestDispatcher(Router.CREATE_DOCTOR_ACCOUNT_CONTROLLER).forward(request,response);
                break;
            default:
                request.setAttribute("errorMessage", "This action is not support or exist");
                request.getRequestDispatcher(Router.ERROR_PAGE).forward(request,response);
        }
    }
}
