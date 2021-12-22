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
        String path = (String) request.getAttribute(Attribute.PATH);

        switch (path) {
            case PathValue.GUEST.LOGIN:
                request.getRequestDispatcher(Router.COMMON.LOGIN_CONTROLLER).forward(request, response);
                break;
            case PathValue.GUEST.CREATE:
                request.getRequestDispatcher(Router.COMMON.CREATE_ACCOUNT_CONTROLLER).forward(request, response);
                break;
            default:
                request.getRequestDispatcher(Router.PAGE.LOGIN_PAGE).forward(request, response);
        }
    }
}
