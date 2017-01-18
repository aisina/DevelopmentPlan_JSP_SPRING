package MVC_PROJECT.model.dao_hibernate;

import MVC_PROJECT.model.exceptions.EmployeeDAOException;

import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 11.01.2017.
 */
public abstract class AbstractEmployeeListDAO<E, K> {
    public abstract Map<K, E> getAll() throws EmployeeDAOException;
    public abstract List<E> getAllList() throws EmployeeDAOException;
    public abstract E getEntityById(K key) throws EmployeeDAOException;
    public abstract boolean update(E entity) throws EmployeeDAOException;
    public abstract boolean delete(K id) throws EmployeeDAOException;
    public abstract String create(E entity) throws EmployeeDAOException;
    public abstract String getNextNewId() throws EmployeeDAOException;

}
