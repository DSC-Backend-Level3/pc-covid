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

@WebServlet(name = "AddVaccineController", value = "/AddVaccineController")
public class AddVaccineController extends HttpServlet {
    private final String PAGE_RETURN = "viewVaccine?btAction=View+Vaccine";
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

        VaccineDTO vaccine = new VaccineDTO(id, name, firm, country, interval);

        return vaccineDao.addNewVaccine(vaccine);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            postHandler(request, response);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Im here");
            response.sendRedirect(PAGE_RETURN);
        }
    }
}
