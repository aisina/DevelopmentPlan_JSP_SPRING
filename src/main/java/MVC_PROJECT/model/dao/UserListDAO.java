package MVC_PROJECT.model.dao;

import MVC_PROJECT.controller.GeneratePassword;
import MVC_PROJECT.model.User;
import MVC_PROJECT.controller.db.DatabaseConnection;
import MVC_PROJECT.model.exceptions.UserDAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
@Repository
public class UserListDAO extends AbstractUserListDAO<User,String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserListDAO.class);

    @Override
    public Map<String, User> getAll() throws UserDAOException {
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
            throw new UserDAOException();
        }
    }

    @Override
    public List<User> getAllList() throws UserDAOException{
        return null;
    }

    @Override
    public User getEntityById(String key) throws UserDAOException{
        return null;
    }

    @Override
    public boolean update(User user) throws UserDAOException{
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
            LOGGER.warn(e.getMessage());
            throw new UserDAOException();
        }
    }

    @Override
    public boolean delete(String id) throws UserDAOException{
        return false;
    }

    @Override
    public String create(User entity) throws UserDAOException{
        return null;
    }

    @Override
    public String getNextNewId() throws UserDAOException{
        return null;
    }
}
