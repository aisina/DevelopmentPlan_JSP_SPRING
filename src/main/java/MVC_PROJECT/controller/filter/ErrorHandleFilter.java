package MVC_PROJECT.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by innopolis on 09.01.2017.
 */
@WebFilter(urlPatterns = {"/addEmployee", "/showEmployeeList", "/deleteEmployee", "/showPlan", "/addPlan",
        "/userPlanView", "/showChangePassAndLoginView", "/changePassAndLogin", "/userPage"})
//@WebFilter(urlPatterns = {"/addEmployee"})
public class ErrorHandleFilter implements Filter{

    private static Set<String> adminList = new HashSet<>();
    private static Set<String> userList = new HashSet<>();

    static {
        //adminList.add("");

        adminList.add("/addEmployee");
        adminList.add("/showEmployeeList");
        adminList.add("/deleteEmployee");
        adminList.add("/showPlan");
        adminList.add("/addPlan");

        userList.add("/showChangePassAndLoginView");
        userList.add("/changePassAndLogin");
        userList.add("/userPage");
        userList.add("/userPlanView");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String query = httpRequest.getRequestURI();

        if ((session == null || session.getAttribute("logged") == null))
                /*&& !query.matches("/public/.*(.css|.js|.png|.jpg|.jsp)")*/
        //&& whiteList.contains(query))
        {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/");

        } else {

            if("admin".equals(session.getAttribute("username"))){
                if(! adminList.contains(query)){
                    //без localhost вообще не работает
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "localhost:8080/pages/adminPage.jsp");
                }
                else{
                    chain.doFilter(httpRequest, httpResponse);
                }

            }
            else{
                if(! userList.contains(query)){
                    //без localhost выдает "Привет, null"
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "localhost:8080/pages/employeePage.jsp");
                }
                else{
                    chain.doFilter(httpRequest, httpResponse);
                }
            }

        }
    }

    @Override
    public void destroy() {

    }
}
