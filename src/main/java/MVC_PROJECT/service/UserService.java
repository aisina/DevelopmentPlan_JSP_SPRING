package MVC_PROJECT.service;

import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.AbstractUserListDAO;
import MVC_PROJECT.model.exceptions.UserDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by innopolis on 05.01.2017.
 */
@Service
public class UserService implements IUserService{

    private final AbstractUserListDAO userDAO;

    @Autowired
    public UserService(AbstractUserListDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean changeLogAndPass(User user) throws UserDAOException {

        boolean isChanged = true;

        String login = user.getUsername();
        String login2 = user.getConfirmUsername();
        String password = user.getPassword();
        String password2 = user.getConfirmPassword();

        System.out.println("UserService; id=" + user.getId() + " new login=" + login + " " + login2 + " new pass=" + password + " " + password2);

        if(! login.equals(login2))
            isChanged = false;
        else{
            if(! password.equals(password2)){
                isChanged = false;
            }
            else{

                boolean bool = userDAO.update(user);
                if(bool){
                }else{
                    isChanged = false;
                }
            }
        }
        return isChanged;
    }
}
