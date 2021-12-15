package controller.dispatcher;

import constant.Router;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDispatcher", value = "/UserDispatcher")
public class UserDispatcher extends HttpServlet {
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
            case "update-password":
                request.getRequestDispatcher(Router.CHANGE_PASSWORD_CONTROLLER).forward(request,response);
                break;
            case "update-info":
                request.getRequestDispatcher(Router.UPDATE_PROFILE_CONTROLLER).forward(request,response);
                break;
            case "vaccination-info":
                request.getRequestDispatcher(Router.USER_VACCINATION_INFO_CONTROLLER).forward(request,response);
                break;
            case "view":
                request.getRequestDispatcher(Router.VIEW_PROFILE_CONTROLLER).forward(request,response);
                break;
            default:
                request.setAttribute("errorMessage", "This action is not support or exist");
                request.getRequestDispatcher(Router.ERROR_PAGE).forward(request,response);
        }
    }
}
