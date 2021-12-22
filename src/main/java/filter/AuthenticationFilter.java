package filter;

import constant.Attribute;
import constant.Router;

import javax.el.MethodNotFoundException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constant.PathValue.HOME_PAGE;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*", dispatcherTypes = DispatcherType.REQUEST)
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
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("Cache-Control","no-cache, no-store");
            String path = req.getRequestURI().substring(req.getContextPath().length());
            if (path.startsWith("/static/") || path.contains(".html") || path.contains(".jsp")) {
                chain.doFilter(request, response);
            }else {
                request.setAttribute(Attribute.PATH, path);
                //get session
                HttpSession session = req.getSession(false);
                //check if session is exist
                if (session != null) { //is exist
                    String id = (String) session.getAttribute(Attribute.USER.USER_ID);
                    if (id == null) {
                        session.setAttribute(Attribute.USER.ROLE, 0);
                    }else{
                        System.out.println(path);
                        if (path.equals("/")){
                            path = "/" + HOME_PAGE;
                            request.setAttribute(Attribute.PATH, path);

                        }
                    }

                    //forward request
                    request.getRequestDispatcher(Router.DISPATCHER.REQUEST_DISPATCHER).forward(request,response);
                } else { //is not exist
                    res.setHeader("Cache-Control","no-cache, no-store");
                    request.setAttribute(Attribute.ERROR.ERROR_MESSAGE, "Please log in first!");
                    request.getRequestDispatcher(Router.PAGE.LOGIN_PAGE).forward(request, response);
                }
                request.setAttribute(Attribute.PATH, path);
            }

        } catch (ServletException | IOException | MethodNotFoundException ex) {
            ex.printStackTrace();
            //forward to error page
            request.getRequestDispatcher(Router.PAGE.ERROR_PAGE).forward(request, response);
        }
    }
}