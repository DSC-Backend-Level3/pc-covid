package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RequestDispatcher", value = "/RequestDispatcher")
public class RequestDispatcher extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String url = "localhost:8080/pc-covid/add";
        request.getServletPath();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
