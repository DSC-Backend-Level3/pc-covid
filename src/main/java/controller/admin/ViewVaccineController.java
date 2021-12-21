package controller.admin;

import constant.Router;
import dao.VaccineDao;
import dao.implement.VaccineDaoImpl;
import dto.VaccineDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static constant.Router.PAGE.ERROR_PAGE;
import static constant.Router.PAGE.VACCINATE_LIST_PAGE;

@WebServlet(name = "ViewVaccineController", value = "/ViewVaccineController")
public class ViewVaccineController extends HttpServlet {
    protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        VaccineDao vaccineDao = new VaccineDaoImpl();
        List<VaccineDTO> vaccineList = null;

        vaccineList = vaccineDao.getAllVaccines();
        request.setAttribute("vaccineList", vaccineList);

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
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(VACCINATE_LIST_PAGE);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
