package controller.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * This controller handle create account request from user
 */
@WebServlet(name = "CreateAccountController", value = "/CreateAccountController")
public class CreateAccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //initialize resources
        //get parameter
        //validate variable
        //add new account to database
        //return to login page with success notification
    }
}
