package controller.user;

import constant.Attribute;
import dao.implement.DistrictDaoImpl;
import dao.implement.ResidentDaoImpl;
import dao.implement.WardDaoImpl;
import dto.DistrictDTO;
import dto.ResidentDTO;
import dto.WardDTO;
import utils.GetParam;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static constant.Router.*;
import static constant.Router.PAGE.ERROR_PAGE;
import static constant.Router.USER.VIEW_PROFILE_CONTROLLER;

@WebServlet(name = "UpdateProfileController", value = "/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String firstName = request.getParameter("txtFirstName");
        String lastName = request.getParameter("txtLastName");
        String phoneNumber = request.getParameter("txtPhoneNumber");
        String email = request.getParameter("txtEmail");
        String healthInsuranceID = request.getParameter("txtHealthInsuranceID");
        String gender = request.getParameter("cboGender");
        Timestamp DOB = Timestamp.valueOf(request.getParameter("txtDOB"));
        String nationality = request.getParameter("txtNationality");
        String wardRequest = request.getParameter("cboWard");
        String houseNumber = request.getParameter("txtHouseNumber");
        String button = request.getParameter("btUpdate");
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
                    if (wardRequest != null) {
                        Integer wardID = Integer.parseInt(wardRequest);
                        dto = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, genderDB,
                                DOB, nationality, wardID, houseNumber, null, null);
                    }else{
                        dto = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, genderDB,
                                DOB, nationality, null, houseNumber, null, null);
                    }
                        ResidentDaoImpl dao = new ResidentDaoImpl();
                        request.setAttribute("PROFILE_PAGE", dto);
                        dao.updateResidentInformation(dto);
                        if(button.equalsIgnoreCase("Save changes")) {
                            url = VIEW_PROFILE_CONTROLLER;
                        }

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

}
