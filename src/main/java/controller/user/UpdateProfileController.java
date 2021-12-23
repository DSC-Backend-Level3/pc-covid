package controller.user;

import constant.Attribute;
import constant.Router;
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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

import static constant.Router.*;
import static constant.Router.PAGE.ERROR_PAGE;
import static constant.Router.PAGE.UPDATE_USER_PROFILE;
import static constant.Router.USER.VIEW_PROFILE_CONTROLLER;

@WebServlet(name = "UpdateProfileController", value = "/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {
    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, NoSuchAlgorithmException, IOException, DateTimeParseException {
        HttpSession session = request.getSession(false);
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter(Attribute.USER.FIRST_NAME);
        if (firstName.length() > 50) {
            throw new IllegalArgumentException("First name invalid!");
        }

        String lastName = request.getParameter(Attribute.USER.LAST_NAME);
        if (lastName.length() > 50) {
            throw new IllegalArgumentException("Last name invalid!");
        }

        String phoneNumber = request.getParameter(Attribute.USER.PHONE_NUMBER);
        if (phoneNumber.length() == 0) {
            phoneNumber = null;
        } else if ((phoneNumber != null && phoneNumber.length() != 10)) {
            throw new IllegalArgumentException("Phone number must have 10 numbers!");
        } else if (!phoneNumber.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Phone number can not contain letters");
        }

        String email = request.getParameter(Attribute.USER.EMAIL);


        String healthInsuranceID = request.getParameter(Attribute.USER.HEALTH_INSURANCE_ID);
        if (healthInsuranceID.length() == 0) {
            healthInsuranceID = null;
        } else if (healthInsuranceID.length() != 15) {
            throw new IllegalArgumentException("Heath Insurance ID must have 15 characters!");
        }
        String gender = request.getParameter(Attribute.USER.GENDER);
        String DOB = request.getParameter(Attribute.USER.DOB);
        String nationality = request.getParameter(Attribute.USER.NATIONALITY);
        if (nationality.length() > 25) {
            throw new IllegalArgumentException("Nationality invalid!");
        }

        String wardRequest = request.getParameter("cboWard");
        String houseNumber = request.getParameter("houseNumber");

        ResidentDTO dto = null;
        String url = "view?btAction=UpdateProfile";
        if (session != null) {
            String id = (String) session.getAttribute(Attribute.USER.USER_ID);

            if (id != null) {
                Timestamp date = Helper.convertDate(DOB);
                ResidentDaoImpl residentDao = new ResidentDaoImpl();
                ResidentDTO resident = residentDao.getResidentById(id);
                if (resident != null) {
                    Integer wardID = resident.getWardID();
                    int roleID = resident.getRoleID();
                    if (wardRequest != null && !wardRequest.equalsIgnoreCase("Select ward")) {
                        wardID = Integer.parseInt(wardRequest);
                        dto = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender,
                                date, nationality, wardID, houseNumber, roleID, null);
                        ResidentDaoImpl dao = new ResidentDaoImpl();
                        request.setAttribute("PROFILE_PAGE", dto);
                        dao.updateResidentInformation(dto);
                    }
                }
            }
        }

    }

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
                    if (district != null) {
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
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateProfile(request, response);
            System.out.println("hello");
            response.sendRedirect("view?btAction=ViewProfile");
        } catch (DateTimeParseException e) {
            request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, "Date invalid");
            RequestDispatcher rd = request.getRequestDispatcher(VIEW_PROFILE_CONTROLLER + "?btAction=UpdateProfile");
            rd.forward(request, response);
        } catch (SQLException e) {
            log(e.getMessage());
            request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, e.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        } catch (NamingException | NoSuchAlgorithmException e) {
            log(e.getMessage());
            request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, e.getMessage());
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher(VIEW_PROFILE_CONTROLLER + "?btAction=UpdateProfile");
            rd.forward(request, response);
        }
    }

}