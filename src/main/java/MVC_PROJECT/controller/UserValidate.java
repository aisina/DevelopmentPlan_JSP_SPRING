package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;

/**
 * Created by innopolis on 05.01.2017.
 */
public class UserValidate {

    public User validateLogin(String name, String password, User user) {

        // All parameters must be valid
        if (name == null || password == null || (name == null && password == null) || name == "" || password == "" || (name == "" && password == "")){
            return null;
        }

        if (user == null){
            return null;
        }

        // Check if the password is valid
        //if (!user.getPassword().equals(password.trim())){
        if (!user.getPassword().equals(password)){
            return null;
        }
        return user;
    }

}
