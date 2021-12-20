package controller.doctor;

import constant.Router;
import dao.ResidentDao;
import dao.VaccinationInfoDao;
import dao.VaccineDao;
import dao.implement.ResidentDaoImpl;
import dao.implement.VaccinationInfoDaoImpl;
import dao.implement.VaccineDaoImpl;
import dto.ResidentDTO;
import dto.VaccinationInfoDTO;
import dto.VaccineDTO;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewVaccinationInfoController", value = "/ViewVaccinationInfoController")
public class ViewVaccinationInfoController extends HttpServlet {

    protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        VaccinationInfoDao vaccinationInfoDao = new VaccinationInfoDaoImpl();
        ResidentDao residentDao = new ResidentDaoImpl();
        VaccineDao vaccineDao = new VaccineDaoImpl();
        List<VaccinationInfoDTO> vaccinationInfoList = null;
        List<ResidentDTO> residentList = new ArrayList<>();
        List<VaccineDTO> vaccineList = new ArrayList<>();

        vaccinationInfoList = vaccinationInfoDao.getAllVaccinationInfo();
        for (VaccinationInfoDTO vaccinationInfo : vaccinationInfoList) {
            String residentID = vaccinationInfo.getResidentID();
            int vaccineID = vaccinationInfo.getVaccineID();

            ResidentDTO resident = residentDao.getResidentById(residentID);
            VaccineDTO vaccine = vaccineDao.getVaccineByID(vaccineID);

            residentList.add(resident);
            vaccineList.add(vaccine);
        }
        request.setAttribute("vaccinationInfoList", vaccinationInfoList);
        request.setAttribute("residentList", residentList);
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
            request.getRequestDispatcher(Router.ERROR_PAGE).forward(request, response);
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(Router.VACCINATION_INFO_LIST_PAGE);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
