package MVC_PROJECT.model.dao;

import MVC_PROJECT.model.exceptions.PlanDAOException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by innopolis on 10.01.2017.
 */
public abstract class AbstractPlanDAO<Plan> {

    public abstract List<Plan> getAll() throws PlanDAOException;
    public abstract void deleteById(int id) throws PlanDAOException;
    public abstract boolean add(Plan plan) throws UnsupportedEncodingException, PlanDAOException;
    public abstract List<Plan> getPlanByYear(String year) throws PlanDAOException;
    public abstract List<Plan> getPlanByEmplId(String id) throws PlanDAOException;
}
