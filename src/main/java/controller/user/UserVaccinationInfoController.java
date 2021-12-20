package controller.user;

import constant.Attribute;
import dao.VaccineDao;
import dao.implement.*;
import dto.*;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constant.Router.*;
import static constant.Router.PAGE.ERROR_PAGE;
import static constant.Router.PAGE.VIEW_VACCINATION_INFO;

@WebServlet(name = "UserVaccinationInfoController", value = "/UserVaccinationInfoController")
public class UserVaccinationInfoController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);

        String url = ERROR_PAGE;
        try {
            if (session != null) {
                String idNumber = (String) session.getAttribute(Attribute.USER.USER_ID);
                System.out.println(idNumber);
                if (idNumber != null) {
                    ResidentDaoImpl userDao = new ResidentDaoImpl();
                    ResidentDTO resident = userDao.getResidentById(idNumber);

                    VaccinationInfoDaoImpl dao = new VaccinationInfoDaoImpl();
                    List<VaccinationInfoDTO> list = dao.getVaccinationInfoByIdUser(idNumber);

                    VaccineDaoImpl daoVaccine = new VaccineDaoImpl();
                    ArrayList<VaccineDTO> listVaccine = new ArrayList<>();

                    ProvinceDaoImpl daoProvince = new ProvinceDaoImpl();
                    ArrayList<ProvinceDTO> listProvince = new ArrayList<>();

                    DistrictDaoImpl daoDistrict = new DistrictDaoImpl();
                    ArrayList<DistrictDTO> listDistrict = new ArrayList<>();

                    WardDaoImpl daoWard = new WardDaoImpl();
                    ArrayList<WardDTO> listWard = new ArrayList<>();

                    for (VaccinationInfoDTO dto : list) {
                        listVaccine.add(daoVaccine.getVaccineByID(dto.getVaccineID()));
                        listWard.add(daoWard.getWardByID(dto.getWardID()));
                    }
                    for (WardDTO wardDto : listWard) {
                        listDistrict.add(daoDistrict.getDistrictByID(wardDto.getDistrictID()));
                    }
                    for (DistrictDTO districtDto : listDistrict) {
                        listProvince.add(daoProvince.getProvinceByID(districtDto.getProvinceID()));
                    }

                    request.setAttribute("USER_INFO", resident);
                    request.setAttribute("VACCINES", listVaccine);
                    request.setAttribute("VACCINATION_INFO", list);
                    request.setAttribute("PROVINCES", listProvince);
                    request.setAttribute("DISTRICTS", listDistrict);
                    request.setAttribute("WARDS", listWard);
                    url = VIEW_VACCINATION_INFO;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
