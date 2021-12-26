package controller.admin;

import constant.PathValue;
import constant.Router;
import dao.VaccineDao;
import dao.implement.VaccineDaoImpl;
import dto.VaccineDTO;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "AddVaccineController", value = "/AddVaccineController")
public class AddVaccineController extends HttpServlet {
    private final String PAGE_RETURN = "viewVaccine";
    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, UnsupportedEncodingException {

        VaccineDao vaccineDao = new VaccineDaoImpl();
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        int id;
        String name;
        String country;
        String firm;
        int interval;

        id = Integer.parseInt(request.getParameter("id"));
        name = request.getParameter("name");
        country = request.getParameter("country");
        firm = request.getParameter("firm");
        interval = Integer.parseInt(request.getParameter("interval"));

        if (vaccineDao.getVaccineByID(id) != null) {
            request.setAttribute("existedError", "ID is available!");
            throw new IllegalArgumentException();
        }

        return vaccineDao.addNewVaccine(new VaccineDTO(id, name, firm, country, interval));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (postHandler(request, response) == true) {
                response.sendRedirect(PAGE_RETURN + "?add=success");
            } else {
                request.getRequestDispatcher(Router.PAGE.VACCINATE_FORM).forward(request, response);
            }
        } catch (SQLException | NamingException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
            request.getRequestDispatcher(Router.PAGE.VACCINATE_FORM).forward(request, response);
        } catch (NumberFormatException ex) {
            request.setAttribute("numberError", "You should enter a number string!");
            request.getRequestDispatcher(Router.PAGE.VACCINATE_FORM).forward(request, response);
        } catch (IllegalArgumentException ex) {
            request.getRequestDispatcher(Router.PAGE.VACCINATE_FORM).forward(request, response);
        }
    }
}
