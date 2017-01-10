package MVC_PROJECT.controller;

import MVC_PROJECT.model.Plan;
import MVC_PROJECT.service.AdminPlanService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created by innopolis on 09.01.2017.
 */
@Controller
public class PlanController {

    private AdminPlanService adminPlanService = new AdminPlanService();

    @RequestMapping(value = "/showPlan", method = RequestMethod.GET)
    public ModelAndView showPlan(HttpServletRequest request){

        String planYear = request.getParameter("planYear");
        return adminPlanService.getValues(planYear);
    }

    @RequestMapping(value = "/addPlan", method = RequestMethod.GET)
    public ModelAndView addPlanGet(){
        return new ModelAndView("addPlan", "addNewPlan", new Plan());
    }

    @RequestMapping(value = "/addPlan", method = RequestMethod.POST)
    public ModelAndView addPlanPost(@ModelAttribute("addNewPlan") Plan myPlan) throws UnsupportedEncodingException {

        return adminPlanService.addPlan(myPlan);
    }
}
