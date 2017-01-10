package MVC_PROJECT.model.dao;

import MVC_PROJECT.controller.GeneratePassword;
import MVC_PROJECT.model.User;
import MVC_PROJECT.controller.db.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 03.01.2017.
 */
public class UserListDAO extends AbstractDAO<User,String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserListDAO.class);

    @Override
    public Map<String, User> getAll() {
        try(Connection connection = DatabaseConnection.getConnection();
            Statement stmt = connection.createStatement();) {

            Map<String, User> users = new HashMap<String, User>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                users.put(user.getUsername(), user);
            }
            return users;

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> getAllList() {
        return null;
    }

    @Override
    public User getEntityById(String key) {
        return null;
    }

    @Override
    public boolean update(User user) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String id = user.getId();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select salt from users where id='" + id + "'");
            String salt= "";
            while(rs.next()){
                salt = rs.getString("salt");
            }

            user.setSalt(salt);
            String sqlQuery = "update users set ";

            if("".equals(user.getUsername()) && !"".equals(user.getPassword())){
                sqlQuery += "password='" + GeneratePassword.generatePass(user.getPassword(), user) + "' ";
            }
            if(!"".equals(user.getUsername()) && "".equals(user.getPassword())){
                sqlQuery += "login='" + user.getUsername() + "' ";
            }
            if(!"".equals(user.getUsername()) && !"".equals(user.getPassword())){
                sqlQuery += "password='" + GeneratePassword.generatePass(user.getPassword(), user) + "', login = '" + user.getUsername() +"' ";
            }

            sqlQuery += "where id="+id;

            stmt.executeUpdate(sqlQuery);
            return true;

        } catch (SQLException e) {
            //LOGGER.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String create(User entity) {
        return null;
    }

    @Override
    public String getNextNewId() {
        return null;
    }
}
