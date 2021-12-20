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

@WebServlet(name = "DoctorDispatcher", value = "/DoctorDispatcher")
public class DoctorDispatcher extends HttpServlet {
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
        System.out.println(path);
        switch (path) {
            case PathValue.DOCTOR.ADD_NEW_VACCINATION_INFO:
                request.getRequestDispatcher(Router.DOCTOR.ADD_VACCINATION_INFO_CONTROLLER).forward(request,response);
                break;
            case "/" + PathValue.HOME_PAGE:
                System.out.println("case doctor");
                request.getRequestDispatcher(Router.DOCTOR.VIEW_VACCINATION_CONTROLLER).forward(request, response);
                break;
            default:
                System.out.println("Hello doctor");
                request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, Attribute.ERROR_MESSAGE.NOT_SUPPORTED_ACTION);
                request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request,response);
        }
    }
}
