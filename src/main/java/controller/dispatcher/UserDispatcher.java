package controller.dispatcher;

import constant.Attribute;
import constant.PathValue;
import constant.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDispatcher", value = "/UserDispatcher")
public class UserDispatcher extends HttpServlet {
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
            case PathValue.USER.UPDATE_PASSWORD:
                request.getRequestDispatcher(Router.USER.CHANGE_PASSWORD_CONTROLLER).forward(request, response);
                break;
            case PathValue.USER.UPDATE_INFO:
                request.getRequestDispatcher(Router.USER.UPDATE_PROFILE_CONTROLLER).forward(request, response);
                break;
            case PathValue.USER.VACCINATION_INFO:
                request.getRequestDispatcher(Router.USER.USER_VACCINATION_INFO_CONTROLLER).forward(request, response);
                break;
            case PathValue.USER.VIEW_INFO:
                request.getRequestDispatcher(Router.USER.VIEW_PROFILE_CONTROLLER).forward(request, response);
                break;
            default:
                request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, Attribute.ERROR_MESSAGE.NOT_SUPPORTED_ACTION);
                request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }
}
