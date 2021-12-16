package controller.user;

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

@WebServlet(name = "UserVaccinationInfoController", value = "/UserVaccinationInfoController")
public class UserVaccinationInfoController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idNumber = request.getParameter("txtIdNumber");
        String url = ERROR_PAGE;
        try {
            VaccinationInfoDaoImpl dao = new VaccinationInfoDaoImpl();
            List<VaccinationInfoDTO> list = dao.getVaccinationInfoByIdUser(idNumber);

            VaccineDaoImpl daoVaccine = new VaccineDaoImpl();
            ArrayList<VaccineDTO> listVaccine = null;

            ProvinceDaoImpl daoProvince = new ProvinceDaoImpl();
            ArrayList<ProvinceDTO> listProvince = null;

            DistrictDaoImpl daoDistrict = new DistrictDaoImpl();
            ArrayList<DistrictDTO> listDistrict = null;

            WardDaoImpl daoWard = new WardDaoImpl();
            ArrayList<WardDTO> listWard = null;

            for(VaccinationInfoDTO dto : list){
                listVaccine.add(daoVaccine.getVaccineByID(dto.getVaccineID()));
                listWard.add(daoWard.getWardByID(dto.getWard()));
            }
            for(WardDTO wardDto : listWard){
                listDistrict.add(daoDistrict.getDistrictByID(wardDto.getDistrictID()));
            }
            for(DistrictDTO districtDto : listDistrict){
                listProvince.add(daoProvince.getProvinceByID(districtDto.getProvinceID()));
            }
            request.setAttribute("VACCINE", listVaccine);
            request.setAttribute("VACCINATION_INFO", list);
            request.setAttribute("PROVINCE", listProvince);
            request.setAttribute("DISTRICT", listDistrict);
            request.setAttribute("WARD", listWard);
            url = VIEW_VACCINATION_INFO;
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
