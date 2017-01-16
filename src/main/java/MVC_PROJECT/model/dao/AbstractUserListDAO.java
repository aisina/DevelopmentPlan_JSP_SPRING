package MVC_PROJECT.model.dao;

import MVC_PROJECT.model.exceptions.UserDAOException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 11.01.2017.
 */
public abstract class AbstractUserListDAO<E, K> {

    public abstract Map<K, E> getAll() throws UserDAOException;
    public abstract List<E> getAllList() throws UserDAOException;
    public abstract E getEntityById(K key) throws UserDAOException;
    public abstract boolean update(E entity, HttpSession session) throws UserDAOException;
    public abstract boolean delete(K id) throws UserDAOException;
    public abstract String create(E entity) throws UserDAOException;
    public abstract String getNextNewId() throws UserDAOException;
}
