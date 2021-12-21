package controller.doctor;

import constant.Router;
import dao.*;
import dao.implement.*;
import dto.*;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constant.Router.PAGE.ERROR_PAGE;
import static constant.Router.PAGE.VACCINATION_INFO_LIST_PAGE;

@WebServlet(name = "ViewVaccinationInfoController", value = "/ViewVaccinationInfoController")
public class ViewVaccinationInfoController extends HttpServlet {

    protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        VaccinationInfoDao vaccinationInfoDao = new VaccinationInfoDaoImpl();
        ResidentDao residentDao = new ResidentDaoImpl();
        VaccineDao vaccineDao = new VaccineDaoImpl();
        ProvinceDao provinceDao = new ProvinceDaoImpl();
        DistrictDao districtDao = new DistrictDaoImpl();
        WardDao wardDao = new WardDaoImpl();
        List<LocationDTO> locationList = new ArrayList<>();
        List<VaccinationInfoDTO> vaccinationInfoList;
        List<ResidentDTO> residentList = new ArrayList<>();
        List<VaccineDTO> vaccineList = new ArrayList<>();

        vaccinationInfoList = vaccinationInfoDao.getAllVaccinationInfo();
        for (VaccinationInfoDTO vaccinationInfo : vaccinationInfoList) {
            //get resident list
            String residentID = vaccinationInfo.getResidentID();
            ResidentDTO resident = residentDao.getResidentById(residentID);
            residentList.add(resident);

            //get vaccine list
            int vaccineID = vaccinationInfo.getVaccineID();
            VaccineDTO vaccine = vaccineDao.getVaccineByID(vaccineID);
            vaccineList.add(vaccine);

            //get location list
            int wardID = vaccinationInfo.getWardID();
            WardDTO ward = wardDao.getWardByID(wardID);
            String wardName = ward.getName();
            int districtID = ward.getDistrictID();
            DistrictDTO district = districtDao.getDistrictByID(districtID);
            String districtName = district.getName();
            int provinceID = district.getProvinceID();
            ProvinceDTO province = provinceDao.getProvinceByID(provinceID);
            String provinceName = province.getName();
            locationList.add(new LocationDTO(provinceName, districtName, wardName));
        }
        request.setAttribute("vaccinationInfoList", vaccinationInfoList);
        request.setAttribute("residentList", residentList);
        request.setAttribute("vaccineList", vaccineList);
        request.setAttribute("locationList", locationList);

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
            RequestDispatcher dispatcher = request.getRequestDispatcher(VACCINATION_INFO_LIST_PAGE);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
