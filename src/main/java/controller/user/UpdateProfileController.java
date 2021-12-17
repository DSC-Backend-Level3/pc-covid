package controller.user;

import dao.implement.ResidentDaoImpl;
import dto.ResidentDTO;
import utils.GetParam;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import static constant.Router.*;

@WebServlet(name = "UpdateProfileController", value = "/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("txtID");
        String firstName = request.getParameter("txtFirstName");
        String lastName = request.getParameter("txtLastName");
        String phoneNumber = request.getParameter("txtPhoneNumber");
        String email = request.getParameter("txtEmail");
        String healthInsuranceID = request.getParameter("txtHealthInsuranceID");
        String gender = request.getParameter("cboGender");
        Timestamp DOB = Timestamp.valueOf(request.getParameter("txtDOB"));
        String nationality = request.getParameter("txtNationality");
        int wardID = Integer.parseInt(request.getParameter("cboWard"));
        String houseNumber = request.getParameter("txtHouseNumber");
        String button = request.getParameter("btUpdate");
        String genderDB = null;
        if (gender.equals("Female")) {
            genderDB = "F";
        } else if (gender.equals("Male")) {
            genderDB = "M";
        }
        ResidentDTO dto = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, genderDB,
                DOB, nationality, wardID, houseNumber, null, null);
        String url = ERROR_PAGE;
        try {
            if (button.equals("Save Changes")) {
                ResidentDaoImpl dao = new ResidentDaoImpl();
                dao.updateResidentInformation(dto);
                url = UPDATE_USER_PROFILE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
            out.close();
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
