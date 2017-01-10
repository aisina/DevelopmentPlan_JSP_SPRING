package MVC_PROJECT.model.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 27.12.2016.
 */

/**
 *
 * @param <E> - тип сущности
 * @param <K> - ключ сущности
 */
public abstract class AbstractDAO<E, K> {

    public abstract Map<K, E> getAll();
    public abstract List<E> getAllList();
    public abstract E getEntityById(K key);
    public abstract boolean update(E entity);
    public abstract boolean delete(K id);
    public abstract String create(E entity);
    public abstract String getNextNewId();

}
