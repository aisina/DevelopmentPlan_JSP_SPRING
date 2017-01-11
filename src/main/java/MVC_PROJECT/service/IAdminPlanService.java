package MVC_PROJECT.service;

import MVC_PROJECT.model.Plan;
import MVC_PROJECT.model.exceptions.PlanDAOException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by innopolis on 10.01.2017.
 */
public interface IAdminPlanService {
    public List<Plan> getValues(String year) throws PlanDAOException;
    public List<Plan> addPlan(Plan plan) throws UnsupportedEncodingException, PlanDAOException;
}
