import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.AbstractUserListDAO;
import MVC_PROJECT.model.exceptions.UserDAOException;
import MVC_PROJECT.service.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by innopolis on 11.01.2017.
 */
public class UserServiceTest {

    private static UserService userService;
    private AbstractUserListDAO userDAO;

    @Before
    public void before(){
        userService = new UserService(userDAO);
    }

    @Test
    public void changeLogAndPass_1() throws UserDAOException {
        User user = new User();
        user.setId("-1");
        user.setUsername("username");
        user.setConfirmUsername("confirmUsername");
        user.setPassword("pass");
        user.setConfirmPassword("pass");

        boolean change = userService.changeLogAndPass(user);
        assertTrue("Логины не совпадают", change == false);
    }

    @Test
    public void changeLogAndPass_2() throws UserDAOException {
        User user = new User();
        user.setId("-1");
        user.setUsername("username");
        user.setConfirmUsername("username");
        user.setPassword("pass");
        user.setConfirmPassword("pass2");

        boolean change = userService.changeLogAndPass(user);
        assertTrue("Пароли не совпадают", change == false);
    }
}
