package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;

/**
 * Created by innopolis on 05.01.2017.
 */
public class UserValidate {

    public User validateLogin(String name, String password, User user) {

        if (name == null || password == null || (name == null && password == null) || name == "" || password == "" || (name == "" && password == "")){
            return null;
        }

        if (user == null){
            return null;
        }

        if (!user.getPassword().equals(password)){
            return null;
        }
        return user;
    }

}
