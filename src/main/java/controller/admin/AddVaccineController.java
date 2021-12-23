package controller.admin;

import dao.VaccineDao;
import dao.implement.VaccineDaoImpl;
import dto.VaccineDTO;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name = "AddVaccineController", value = "/AddVaccineController")
public class AddVaccineController extends HttpServlet {
    private final String PAGE_RETURN = "viewVaccine?btAction=viewVaccine";
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

        VaccineDTO vaccine = vaccineDao.getVaccineByID(id);
        if (vaccine != null) {
            request.setAttribute("errorMessage", "ID is existed!");
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
            postHandler(request, response);
            response.sendRedirect(PAGE_RETURN);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            if (e.getMessage().contains("PRIMARY KEY")) {
                request.setAttribute("errorMessage", "The vaccine ID is available!");
            }
            request.getRequestDispatcher(Router.PAGE.VACCINATE_FORM).forward(request, response);
        }
    }
}
