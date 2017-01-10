package MVC_PROJECT.model.dao;

import MVC_PROJECT.model.Plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by innopolis on 27.12.2016.
 */
public interface myDAO<E> {

    public List<E> getAll();
    public void deleteById(int id);
    public boolean add(Plan plan) throws UnsupportedEncodingException;
}
