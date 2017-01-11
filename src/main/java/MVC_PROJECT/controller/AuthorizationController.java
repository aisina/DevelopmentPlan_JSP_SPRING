package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.AbstractUserListDAO;
import MVC_PROJECT.model.dao.UserListDAO;
import MVC_PROJECT.model.exceptions.UserDAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by innopolis on 05.01.2017.
 */

@Controller
public class AuthorizationController {

    public UserValidate userValidate = new UserValidate();
    public GeneratePassword userLogonController = new GeneratePassword();
    public static AbstractUserListDAO userDAO = new UserListDAO();
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);

    private static Map<String, User> users;

    @Autowired
    public Map<String, User> getAll() throws UserDAOException {
        return this.users = userDAO.getAll();
    }


    //кнопка "Выйти"
    @RequestMapping(value = "/userLogout")
    public ModelAndView Logout(HttpServletRequest req, SessionStatus status) throws IOException {

        HttpSession session = req.getSession(false);

        session.setAttribute("user", null);
        session.setAttribute("username", null);
        session.setAttribute("id", null);

        status.setComplete(); //очищает Spring овскую session

        return new ModelAndView("login", "userCommandName", new User());
    }


    @RequestMapping(value = {"/userLogon"}, method = RequestMethod.GET)
    public String Log(HttpServletRequest req, Model model) {
        return "adminPage";
    }



    @RequestMapping(value = {"/userLogon"}, method = RequestMethod.POST)
    public ModelAndView Login(@ModelAttribute("userCommandName") User myUser, HttpServletRequest req) throws ServletException, IOException {

        ModelAndView mav = new ModelAndView();
        User user = userValidate.validateLogin(myUser.getUsername(), userLogonController.generatePass(myUser.getPassword(), users.get(myUser.getUsername())), users.get(myUser.getUsername()));
        if (user == null) {

            mav.setViewName("loginError");
            return mav;

        } else {

            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("id", user.getId());

            if ("admin".contains(user.getUsername())) {
                mav.addObject("authorizedUser", user);
                mav.setViewName("adminPage");
                return mav;

            } else {
                mav.addObject("authorizedUser", user);
                UserPlanView upv = new UserPlanView();
                return upv.showUserPlan(user, mav);
            }
        }
    }


    @RequestMapping(value = {"/logonAgain"}, method = RequestMethod.POST)
    public ModelAndView logonAgain() throws IOException {

        return new ModelAndView("login", "userCommandName", new User());
    }


}
