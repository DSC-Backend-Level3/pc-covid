package controller.admin;

import constant.Role;
import constant.Router;
import dao.ResidentDao;
import dao.implement.ResidentDaoImpl;
import dto.ResidentDTO;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ViewDoctorAccountsController", value = "/ViewDoctorAccountsController")
public class ViewDoctorAccountsController extends HttpServlet {

    protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        ResidentDao residentDao = new ResidentDaoImpl();
        List<ResidentDTO> doctorAccounts = null;

        doctorAccounts = residentDao.getResidentsByRoleId(Role.DOCTOR);
        request.setAttribute("doctorList", doctorAccounts);
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getHandler(request, response);
        } catch (Exception ex) {
            log(ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Router.ERROR_PAGE).forward(request, response);
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(Router.DOCTOR_ACCOUNT_PAGE);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
