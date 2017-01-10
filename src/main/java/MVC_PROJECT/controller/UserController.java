package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;
import MVC_PROJECT.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by innopolis on 05.01.2017.
 */
@Controller
@SessionAttributes("user")
public class UserController {

    UserService userService = new UserService();


    @RequestMapping(value = "/showChangePassAndLoginView", method = RequestMethod.GET)
    public ModelAndView showChangePassAndLoginView(){
        return new ModelAndView("changePassAndLogin", "authorizedUser", new User());
    }


    @RequestMapping(value = "/changePassAndLogin", method = RequestMethod.POST)
    public ModelAndView changePassAndLogin(@ModelAttribute User user){
        //в ModelAttribute User user используются данные из HttpSerssion сессии
        ModelAndView mav = userService.changeLogAndPass(user);
        return mav;
    }

    @RequestMapping(value = "/userPage")
    public ModelAndView userPage(HttpSession session) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();

        //user не передается
        User user = new User();
        user.setId((String) session.getAttribute("id"));

        mav.addObject("authorizedUser", user);
        UserPlanView upv = new UserPlanView();
        return upv.showUserPlan(user, mav);
    }

}
