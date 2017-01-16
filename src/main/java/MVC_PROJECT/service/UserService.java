package MVC_PROJECT.service;

import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.AbstractUserListDAO;
import MVC_PROJECT.model.exceptions.UserDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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
    @Secured("ROLE_USER")
    public boolean changeLogAndPass(User user, HttpSession session) throws UserDAOException {

        boolean isChanged = true;

        String login = user.getUsername();
        String login2 = user.getConfirmUsername();
        String password = user.getPassword();
        String password2 = user.getConfirmPassword();

        if(! login.equals(login2))
            isChanged = false;
        else{
            if(! password.equals(password2)){
                isChanged = false;
            }
            else{

                boolean bool = userDAO.update(user, session);
                if(bool){
                }else{
                    isChanged = false;
                }
            }
        }
        return isChanged;
    }
}
