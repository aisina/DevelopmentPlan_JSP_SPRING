package MVC_PROJECT.model.dao_hibernate;

import MVC_PROJECT.model.exceptions.PlanDAOException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by innopolis on 16.01.2017.
 */
public class PlanDAO_H extends AbstractPlanDAO{
    @Override
    public List getAll() throws PlanDAOException {
        return null;
    }

    @Override
    public void deleteById(int id) throws PlanDAOException {

    }

    @Override
    public boolean add(Object o) throws UnsupportedEncodingException, PlanDAOException {
        return false;
    }

    @Override
    public List getPlanByYear(String year) throws PlanDAOException {
        return null;
    }

    @Override
    public List getPlanByEmplId(String id) throws PlanDAOException {
        return null;
    }
}
