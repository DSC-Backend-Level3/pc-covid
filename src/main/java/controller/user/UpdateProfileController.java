package controller.user;

import constant.Attribute;
import dao.implement.DistrictDaoImpl;
import dao.implement.ProvinceDaoImpl;
import dao.implement.ResidentDaoImpl;
import dao.implement.WardDaoImpl;
import dto.DistrictDTO;
import dto.ProvinceDTO;
import dto.ResidentDTO;
import dto.WardDTO;
import utils.Helper;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static constant.Router.*;
import static constant.Router.PAGE.ERROR_PAGE;
import static constant.Router.PAGE.UPDATE_USER_PROFILE;
import static constant.Router.USER.VIEW_PROFILE_CONTROLLER;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@WebServlet(name = "UpdateProfileController", value = "/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String province = request.getParameter("cboProvince");
        String district = request.getParameter("cboDistrict");
        String url = ERROR_PAGE;
        try {
            if (session != null) {
                String id = (String) session.getAttribute(Attribute.USER.USER_ID);
                if (province != null) {
                    //get district list by provinceID
                    int provinceID = Integer.parseInt(province);
                    DistrictDaoImpl districtDao = new DistrictDaoImpl();
                    List<DistrictDTO> list = districtDao.getDistrictByProvinceID(provinceID);

                    //get resident by ID
                    ResidentDaoImpl dao = new ResidentDaoImpl();
                    ResidentDTO dto = dao.getResidentById(id);


                    ProvinceDaoImpl provinceDao = new ProvinceDaoImpl();
                    ProvinceDTO province1 = provinceDao.getProvinceByID(provinceID);
                    List<ProvinceDTO> listrProvince = provinceDao.getAllProvinces();

                    request.setAttribute("PROFILE_PAGE", dto);
                    request.setAttribute("PROFILE_PROVINCE", province1);
                    request.setAttribute("PROVINCE_LIST", listrProvince);
                    request.setAttribute("DISTRICT_LIST", list);
                    if(district != null){
                        //get district
                        int districtID = Integer.parseInt(district);
                        DistrictDTO districtDTO = districtDao.getDistrictByID(districtID);
                        request.setAttribute("PROFILE_DISTRICT", districtDTO);

                        //get ward list by districtID
                        WardDaoImpl wardDao = new WardDaoImpl();
                        List<WardDTO> listWard = wardDao.getWardByDistrictID(districtID);
                        request.setAttribute("WARD_LIST", listWard);

                    }//If select district

                    url = UPDATE_USER_PROFILE;
                }//If select province
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter(Attribute.USER.FIRST_NAME);
        String lastName = request.getParameter(Attribute.USER.LAST_NAME);
        String phoneNumber = request.getParameter(Attribute.USER.PHONE_NUMBER);
        String email = request.getParameter(Attribute.USER.EMAIL);
        String healthInsuranceID = request.getParameter(Attribute.USER.HEALTH_INSURANCE_ID);
        String gender = request.getParameter(Attribute.USER.GENDER);
        String DOB = request.getParameter(Attribute.USER.DOB);
        String nationality = request.getParameter(Attribute.USER.NATIONALITY);
        String wardRequest = request.getParameter("cboWard");
        String houseNumber = request.getParameter("txtHouseNumber");
        String genderDB = null;
        if (gender.equals("Female")) {
            genderDB = "F";
        } else if (gender.equals("Male")) {
            genderDB = "M";
        }
        ResidentDTO dto = null;
        String url = ERROR_PAGE;
        try {
            if (session != null) {
                String id = (String) session.getAttribute(Attribute.USER.USER_ID);
                if (id != null) {

                    Timestamp date = Helper.convertDate(DOB);
                    ResidentDaoImpl residentDao = new ResidentDaoImpl();
                    ResidentDTO resident = residentDao.getResidentById(id);
                    int roleID = resident.getRoleID();
                    if (wardRequest != null) {
                        Integer wardID = Integer.parseInt(wardRequest);
                        dto = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, genderDB,
                                date, nationality, wardID, houseNumber, roleID, null);
                        ResidentDaoImpl dao = new ResidentDaoImpl();
                        request.setAttribute("PROFILE_PAGE", dto);
                        dao.updateResidentInformation(dto);
                        url = VIEW_PROFILE_CONTROLLER;
                    }

                }
            }

        }catch (DateTimeParseException e){
                request.setAttribute("ERROR", "Invalid date.");
                url = VIEW_PROFILE_CONTROLLER + "?btAction=Update Profile";

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

}
