package MVC_PROJECT.controller;

import MVC_PROJECT.model.User;

/**
 * Created by innopolis on 05.01.2017.
 */
public class GeneratePassword {

    public static String generatePass(String str, User user){
        if(user == null)
            return null;
        else{
            String string = PasswordSalt.md5Custom(str);
            string += user.getSalt();
            return PasswordSalt.md5Custom(string);
        }

    }
}
