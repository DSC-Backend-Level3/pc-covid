package filter;

import constant.Router;
import utils.Helper;

import javax.el.MethodNotFoundException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            //cast ServletRequest to HttpServletRequest
            HttpServletRequest req = (HttpServletRequest) request;
            //check if guest is login or not
            if (!Helper.isLogin(req)) { //is not login
                HttpSession session = req.getSession();
                session.setAttribute("role", 0);
            }
            //do filter
            chain.doFilter(request,response);
        } catch (ServletException | IOException | MethodNotFoundException ex) {
            //forward to error page
            request.getRequestDispatcher(Router.ERROR_PAGE).forward(request,response);
        }
    }
}
