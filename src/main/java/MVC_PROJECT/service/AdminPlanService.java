package MVC_PROJECT.service;

import MVC_PROJECT.model.Plan;
import MVC_PROJECT.model.dao_hibernate.AbstractPlanDAO;
import MVC_PROJECT.model.exceptions.PlanDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by innopolis on 09.01.2017.
 */
@Service
public class AdminPlanService implements IAdminPlanService{

    //private final AbstractPlanDAO<Plan> storeOfPlan;
    private final AbstractPlanDAO<Plan> storeOfPlan;

    @Autowired
    public AdminPlanService(AbstractPlanDAO<Plan> storeOfPlan) {
        this.storeOfPlan = storeOfPlan;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<Plan> getValues(String year) throws PlanDAOException {
        return storeOfPlan.getPlanByYear(year);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<Plan> addPlan(Plan plan) throws UnsupportedEncodingException, PlanDAOException {
        boolean bool = storeOfPlan.add(plan);
        if(bool)
            return storeOfPlan.getPlanByYear(plan.getYear());
        else return new ArrayList<>();
    }
}
