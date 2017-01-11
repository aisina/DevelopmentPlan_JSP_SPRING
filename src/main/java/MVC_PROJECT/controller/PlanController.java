package MVC_PROJECT.controller;

import MVC_PROJECT.model.Plan;
import MVC_PROJECT.model.exceptions.PlanDAOException;
import MVC_PROJECT.service.IAdminPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by innopolis on 09.01.2017.
 */
@Controller
public class PlanController {

    private final IAdminPlanService adminPlanService;

    @Autowired
    public PlanController(IAdminPlanService adminPlanService) {
        this.adminPlanService = adminPlanService;
    }

    @RequestMapping(value = "/showPlan", method = RequestMethod.GET)
    public ModelAndView showPlan(HttpServletRequest request){

        String planYear = request.getParameter("planYear");
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("adminPlanByYear", adminPlanService.getValues(planYear));
            mav.setViewName("adminPlan");
        } catch (PlanDAOException e) {
            mav.setViewName("errorPage");
        }
        return mav;
    }

    @RequestMapping(value = "/addPlan", method = RequestMethod.GET)
    public ModelAndView addPlanGet(){
        return new ModelAndView("addPlan", "addNewPlan", new Plan());
    }

    @RequestMapping(value = "/addPlan", method = RequestMethod.POST)
    public ModelAndView addPlanPost(@ModelAttribute("addNewPlan") Plan myPlan) throws UnsupportedEncodingException {

        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("adminPlanByYear", adminPlanService.addPlan(myPlan));
            mav.setViewName("adminPlan");
        } catch (PlanDAOException e) {
            mav.setViewName("errorPage");
        }
        return mav;
    }
}
