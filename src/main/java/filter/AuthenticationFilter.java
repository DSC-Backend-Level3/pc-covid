package filter;

import utils.Helper;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try{
            //cast ServletRequest to HttpServletRequest
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            //check if guest is login or not
            if(Helper.isLogin(req)){ //is login
                //get user role
                HttpSession session = req.getSession();
                int role = (int) session.getAttribute("role");
                /*
                forward to the correct dispatcher for role
                if(role == admin){ //user role is admin
                    request.getRequestDispatcher("AdminServlet").forward(req,res);
                }
                if(role == doctor){ //user role is doctor
                     request.getRequestDispatcher("DoctorServlet").forward(req,res);
                }
                if(role == user){ //user role is user
                     request.getRequestDispatcher("UserServlet").forward(req,res);
                }
                */
            }else{ //is not login
                //forward to login page
                //request.getRequestDispatcher("LoginServlet").forward(req,res);
            }
            //do filter
            chain.doFilter(request, response);
        }catch (Exception ex){
            //log exception
            //forward to error page
        }
    }
}
