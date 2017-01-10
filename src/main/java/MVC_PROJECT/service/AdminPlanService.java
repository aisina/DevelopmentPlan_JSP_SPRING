package MVC_PROJECT.service;

import MVC_PROJECT.model.Plan;
import MVC_PROJECT.model.dao.PlanDAO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by innopolis on 09.01.2017.
 */
public class AdminPlanService {

    private final PlanDAO storeOfPlan = new PlanDAO();

    public ModelAndView getValues(String year){
        ModelAndView mav = new ModelAndView();
        mav.addObject("adminPlanByYear", storeOfPlan.getPlanByYear(year));
        mav.setViewName("adminPlan");

        return mav;
    }

    public ModelAndView addPlan(Plan plan) throws UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView();
        boolean bool = storeOfPlan.add(plan);
        if(bool){
            mav.addObject("adminPlanByYear", storeOfPlan.getPlanByYear(plan.getYear()));
            mav.setViewName("adminPlan");
        }else{
            mav.setViewName("adminPlan"); //по сути, здесь должна быть страница ошибки
        }
        return mav;
    }
}
