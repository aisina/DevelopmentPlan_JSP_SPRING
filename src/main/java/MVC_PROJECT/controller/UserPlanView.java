package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.PlanDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by innopolis on 05.01.2017.
 */
@Controller
public class UserPlanView {

    private final PlanDAO storeOfPlan = new PlanDAO();

    @RequestMapping(value = {"/userPlanView"}, method = RequestMethod.GET)
    public ModelAndView showUserPlan(User user, ModelAndView mav) throws ServletException, IOException {

            //req.setAttribute("plans", this.controller.getValues(user.getId()));
            //req.getRequestDispatcher("pages/employeePage.jsp").forward(req, resp);
            mav.addObject("plans", storeOfPlan.getPlanByEmplId(user.getId()));
            mav.setViewName("employeePage");
        return mav;
    }

}
