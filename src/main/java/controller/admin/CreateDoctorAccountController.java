package controller.admin;

import constant.Router;
import dao.ResidentDao;
import dao.implement.ResidentDaoImpl;
import dto.ResidentDTO;
import utils.Helper;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;

import static constant.Router.PAGE.DOCTOR_ACCOUNT_FORM;

@WebServlet(name = "CreateDoctorAccountController", value = "/CreateDoctorAccountController")
public class CreateDoctorAccountController extends HttpServlet {
    private final String PAGE_RETURN = "viewDoctor?btAction=View+Doctor";
    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, SQLException, NamingException, DateTimeParseException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ResidentDao residentDao = new ResidentDaoImpl();

        String id;
        String firstName;
        String lastName;
        String phoneNumber;
        String email;
        String healthInsuranceID;
        String gender;
        String DOB;
        String nationality;
        int wardID;
        String houseNumber;
        String password;

        id = request.getParameter("id");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        phoneNumber = request.getParameter("phoneNumber");
        healthInsuranceID = request.getParameter("healthInsuranceID");
        DOB = request.getParameter("DOB");
        nationality = request.getParameter("nationality");
        wardID = Integer.parseInt(request.getParameter("wardID"));
        houseNumber = request.getParameter("houseNumber");
        password = request.getParameter("password");
        email = request.getParameter("email");
        gender = request.getParameter("gender");

        Timestamp date = Helper.convertDate(DOB);
        ResidentDTO residentDTO = new ResidentDTO(id, firstName, lastName, phoneNumber, email, healthInsuranceID, gender, date, nationality, wardID, houseNumber, 3, password);

        return residentDao.addNewResident(residentDTO);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            postHandler(request, response);
        }catch (DateTimeParseException e){
            request.setAttribute("ERROR", "Invalid date.");
            RequestDispatcher rd = request.getRequestDispatcher(DOCTOR_ACCOUNT_FORM);
            rd.forward(request, response);
        }catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(PAGE_RETURN);
        }
    }
}
