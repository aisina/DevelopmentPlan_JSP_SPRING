package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.AbstractDAO;
import MVC_PROJECT.model.dao.UserListDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by innopolis on 05.01.2017.
 */
//login, logout
@Controller
public class AuthorizationController {

    public UserValidate userValidate = new UserValidate();
    public GeneratePassword userLogonController = new GeneratePassword();
    public static AbstractDAO userDAO = new UserListDAO();
    private static final Map<String, User> users = userDAO.getAll();


    //кнопка "Выйти"
    @RequestMapping(value = "/userLogout")
    public ModelAndView Logout(HttpServletRequest req) throws IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession(false);

        session.setAttribute("logged", null);
        session.setAttribute("username", null);
        session.setAttribute("id", null);

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

        //sout убрать, так как если user==null, то б-т NullPointerException
        //System.out.println("Мы авторизуемся, данные=" + user.getId() + " " + user.getUsername());

        if (user == null) {

            mav.setViewName("loginError");
            return mav;

        } else {

            HttpSession session = req.getSession();
            session.setAttribute("logged", user);
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


    //кнопка "Назад", попробовать залогиниться еще раз
    @RequestMapping(value = {"/logonAgain"}, method = RequestMethod.POST)
    public ModelAndView logonAgain(HttpServletRequest req) throws IOException {


        HttpServletRequest httpRequest = (HttpServletRequest) req;
        //HttpServletResponse httpResponse = (HttpServletResponse) resp;

       /* HttpSession session = httpRequest.getSession(false);
        session.setAttribute("logged", null);
        session.setAttribute("username", null);
        session.setAttribute("id", null);*/

        //httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");

        return new ModelAndView("login", "userCommandName", new User());
    }


}
