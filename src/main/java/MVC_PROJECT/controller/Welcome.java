package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.AbstractUserListDAO;
import MVC_PROJECT.model.dao.UserListDAO;
import MVC_PROJECT.model.exceptions.UserDAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;

/**
 * Created by innopolis on 05.01.2017.
 */
@Controller
@SessionAttributes("user")
public class Welcome {

    private static final Logger LOGGER = LoggerFactory.getLogger(Welcome.class);

    public UserValidate userValidate = new UserValidate();
    public static AbstractUserListDAO userDAO = new UserListDAO();

    private static Map<String, User> users;

    @Autowired
    public Map<String, User> getAll() throws UserDAOException {
        return this.users = userDAO.getAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView Login(HttpServletRequest req) throws ServletException, IOException {
       String role = "ROLE_USER";
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String username = auth.getName(); //get logged in username

        /*Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();*/
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                auth.getAuthorities();

        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("user", new User());
        mav.addObject("username", username);

        User user = users.get(username);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("id", user.getId());

        if(hasRole){
            mav.setViewName("employeePage");
            UserPlanView upv = new UserPlanView();
            return upv.showUserPlan(user, mav);
        }else{
            mav.setViewName("adminPage");
            return mav;
        }

    }



    @RequestMapping(value = "/userLogout")
    public void Logout(HttpServletRequest req, SessionStatus status) throws IOException {

        HttpSession session = req.getSession(false);

        session.setAttribute("user", null);
        session.setAttribute("username", null);
        session.setAttribute("id", null);

        status.setComplete(); //очищает Spring овскую session
    }


   @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public ModelAndView showAdminPage(@ModelAttribute User user){
       //System.out.println("Welcome controller, user="+user.getId() + ", " + user.getUsername());
        //return "adminPage"; - возвращает "Здравствуйте, !" без Username
       ModelAndView mav = new ModelAndView();
       mav.setViewName("adminPage");
       mav.addObject("username", user.getUsername());
        return mav;
    }
}
