package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by innopolis on 05.01.2017.
 */
@Controller
public class Welcome {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    /*public String Login(){
        return "login";
    }*/
    public ModelAndView Login(){
        //login - название представления
        //аналогично созданию
        //mav.addObject("userCommandName", user);
        //mav.setViewName("login");
        return new ModelAndView("login", "userCommandName", new User());
    }


   @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public ModelAndView showAdminPage(HttpSession session){
        //return "adminPage"; - возвращает "Здравсьвуйте, !" без Username
       ModelAndView mav = new ModelAndView();
       mav.setViewName("adminPage");
       mav.addObject("username", session.getAttribute("username"));
        return mav;
    }
}
