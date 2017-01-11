package MVC_PROJECT.service;

import MVC_PROJECT.model.User;
import MVC_PROJECT.model.exceptions.UserDAOException;

/**
 * Created by innopolis on 10.01.2017.
 */
public interface IUserService {
    public boolean changeLogAndPass(User user) throws UserDAOException;

}
