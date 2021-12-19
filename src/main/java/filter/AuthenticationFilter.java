package filter;

import constant.Attribute;
import constant.Router;

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

            String path = req.getRequestURI().substring(req.getContextPath().length());
            if (path.startsWith("/static/")) {
                chain.doFilter(request, response);
            } else {
                request.setAttribute(Attribute.PATH, path);
                //get session
                HttpSession session = req.getSession(false);
                //check if session is exist
                if (session != null) { //is exist
                    String id = (String) session.getAttribute(Attribute.USER.USER_ID);
                    if (id == null) {
                        session.setAttribute(Attribute.USER.ROLE, 0);
                    }
                    //forward request
                    request.getRequestDispatcher(Router.DISPATCHER.REQUEST_DISPATCHER).forward(request,response);
                } else { //is not exist
                    request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, "Please log in first!");
                    request.getRequestDispatcher(Router.PAGE.LOGIN_PAGE).forward(request, response);
                }
            }
        } catch (ServletException | IOException | MethodNotFoundException ex) {
            //forward to error page
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }
}
