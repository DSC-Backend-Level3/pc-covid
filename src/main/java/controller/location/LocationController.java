package controller.location;

import com.google.gson.Gson;
import dao.DistrictDao;
import dao.ProvinceDao;
import dao.WardDao;
import dao.implement.DistrictDaoImpl;
import dao.implement.ProvinceDaoImpl;
import dao.implement.WardDaoImpl;
import dto.DistrictDTO;
import dto.ProvinceDTO;
import dto.WardDTO;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LocationController", value = "/LocationController")
public class LocationController extends HttpServlet {
    protected void getHandler(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        ProvinceDao provinceDao = new ProvinceDaoImpl();
        DistrictDao districtDao = new DistrictDaoImpl();
        WardDao wardDao = new WardDaoImpl();


        //get parameter
        String option = request.getParameter("operation");

        //get province list
        if (option.equals("province")) {
            try {
                List<ProvinceDTO> list = provinceDao.getAllProvinces();
                if (list == null) {
                    request.setAttribute("provinceError", "Province list is empty");
                    return;
                }
                Gson json = new Gson();
                String provinceList = json.toJson(list);
                response.setContentType("text/html");
                response.getWriter().write(provinceList);
            } catch (SQLException | NamingException | IOException ex) {
                ex.printStackTrace();
            }
        }

        //get district list
        if (option.equals("district")) {
            try {
                int provinceID = Integer.parseInt(request.getParameter("id"));
                List<DistrictDTO> list = districtDao.getDistrictByProvinceID(provinceID);
                if (list == null) {
                    request.setAttribute("districtError", "District list is empty");
                    return;
                }
                Gson json = new Gson();
                String provinceList = json.toJson(list);
                response.setContentType("text/html");
                response.getWriter().write(provinceList);
            } catch (SQLException | NamingException | IOException ex) {
                ex.printStackTrace();
            }
        }

        //get ward list
        if (option.equals("ward")) {
            try {
                int districtID = Integer.parseInt(request.getParameter("id"));
                List<WardDTO> list = wardDao.getWardByDistrictID(districtID);
                if (list == null) {
                    request.setAttribute("wardError", "Ward list is empty");
                    return;
                }
                Gson json = new Gson();
                String provinceList = json.toJson(list);
                response.setContentType("text/html");
                response.getWriter().write(provinceList);
            } catch (SQLException | NamingException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getHandler(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
