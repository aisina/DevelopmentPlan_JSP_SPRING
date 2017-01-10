package MVC_PROJECT.service;

import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.UserListDAO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by innopolis on 05.01.2017.
 */
public class UserService {

    UserListDAO userDAO = new UserListDAO();

    public ModelAndView changeLogAndPass(User user){

        ModelAndView mav = new ModelAndView();
        mav.addObject("authorizedUser", user);

        String login = user.getUsername();
        String login2 = user.getConfirmUsername();
        String password = user.getPassword();
        String password2 = user.getConfirmPassword();

        System.out.println("UserService; id=" + user.getId() + " new login=" + login + " " + login2 + " new pass=" + password + " " + password2);

        if(! login.equals(login2))
            mav.setViewName("loginChangedError");
        else{
            if(! password.equals(password2)){
                //LOGGER.info("Пароли не совпадают");
                mav.setViewName("loginChangedError");
            }
            else{

                boolean bool = userDAO.update(user);
                if(bool){
                    mav.setViewName("loginChangedSuccess");
                }else{
                    mav.setViewName("loginChangedError");
                }
            }
        }
        return mav;
    }


   /* public ModelAndView changeLogAndPassWithSession(HttpSession session, User user){

        System.out.println("Изменение логина и пароля " + (String) session.getAttribute("id") + " " + user.getUsername() + " " + user.getConfirmUsername());

        ModelAndView mav = new ModelAndView();
        user.setId((String) session.getAttribute("id"));
        mav.addObject("authorizedUser", user);

        String login = user.getUsername();
        String login2 = user.getConfirmUsername();
        String password = user.getPassword();
        String password2 = user.getConfirmPassword();

        //System.out.println(login + " " + login2 + " " + password + " " + password2);

        //дополнение
        user.setId((String) session.getAttribute("id"));

        if(! login.equals(login2)) {
            mav.setViewName("loginChangedError");
        }
        else{
            if(! password.equals(password2)){
                mav.setViewName("loginChangedError");
            }
            else{
                boolean bool = userDAO.update(user);
                if(bool){
                    mav.setViewName("loginChangedSuccess");
                    if(! "".equals(login))//если изменили логин, а не только пароль
                        session.setAttribute("username", login);
                }else{
                    mav.setViewName("loginChangedError");
                }
            }
        }
        return mav;
    }*/



}
