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
        String path = (String) request.getAttribute(Attribute.PATH);

        switch (path){
            case PathValue.ADMIN.ADD_NEW_VACCINE:
                request.getRequestDispatcher(Router.ADMIN.ADD_VACCINE_CONTROLLER).forward(request,response);
                break;
            case PathValue.ADMIN.CREATE_DOCTOR_ACCOUNT:
                request.getRequestDispatcher(Router.ADMIN.CREATE_DOCTOR_ACCOUNT_CONTROLLER).forward(request,response);
                break;
            case PathValue.ADMIN.VIEW_DOCTOR_LIST:
                request.getRequestDispatcher(Router.ADMIN.VIEW_DOCTOR_ACCOUNT_CONTROLLER).forward(request,response);
                break;
            case PathValue.DOCTOR.LOAD_LOCATION:

                request.getRequestDispatcher(Router.DOCTOR.LOCATION_CONTROLLER).forward(request,response);
                break;
            case PathValue.ADMIN.VIEW_VACCINE_LIST:

                request.getRequestDispatcher(Router.ADMIN.VIEW_VACCINE_CONTROLLER).forward(request,response);
                break;
            case "/" + PathValue.HOME_PAGE:
                request.getRequestDispatcher(Router.ADMIN.ADMIN_HOME).forward(request, response);
                break;
            default:

                request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, Attribute.ERROR_MESSAGE.NOT_SUPPORTED_ACTION);
                request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request,response);
        }
    }
}
