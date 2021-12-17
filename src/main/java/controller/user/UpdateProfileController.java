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

import static constant.Router.ERROR_PAGE;
import static constant.Router.USER_DISPATCHER;

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
        String gender = request.getParameter("txtGender");
        Timestamp DOB = Timestamp.valueOf(request.getParameter("txtDOB"));
        String nationality = request.getParameter("txtNationality");
        int wardID = Integer.parseInt(request.getParameter("cboWard"));
        String houseNumber = GetParam.getStringParam(request, "txtHouseNumber", "House Number", 1, 10, null);
        ResidentDTO dto = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender,
                DOB, nationality, wardID, houseNumber, null, null);
        String url = ERROR_PAGE;
        try{
            ResidentDaoImpl dao = new ResidentDaoImpl();
            dao.updateResidentInformation(dto);
            url = USER_DISPATCHER;
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
