package MVC_PROJECT.model.dao_hibernate;

import MVC_PROJECT.controller.db.DatabaseConnection;
import MVC_PROJECT.controller.db.HibernateUtil;
import MVC_PROJECT.model.User;
import MVC_PROJECT.model.dao.UserListDAO;
import MVC_PROJECT.model.exceptions.UserDAOException;
import MVC_PROJECT.model.pojo_hibernate.Employee_H;
import MVC_PROJECT.model.pojo_hibernate.User_H;
import MVC_PROJECT.model.pojo_hibernate.User_Roles_H;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 16.01.2017.
 */
public class UserListDAO_H extends AbstractUserListDAO<User_H, String>{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserListDAO.class);

    PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Map<String, User_H> getAll() throws UserDAOException {

        Map<String, User_H> users = new HashMap<String, User_H>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String str = "from User_H";
        Query q = session.createQuery(str);
        List lst = q.list();
        int i = 0;
        while(i < lst.size()){
            User_H user = (User_H) lst.get(i);
            users.put(user.getUsername(), user);
            i++;
        }

        HibernateUtil.shutdown();

        return users;
    }

    @Override
    public List<User_H> getAllList() throws UserDAOException {
        return null;
    }

    @Override
    public User_H getEntityById(String key) throws UserDAOException {
        return null;
    }

    @Override
    public boolean update(User_H user, HttpSession session) throws UserDAOException {
        Session hib_session = HibernateUtil.getSessionFactory().openSession();
        hib_session.beginTransaction();

        String str = "select username from User_H where id = :id";
        Query q = hib_session.createQuery(str);
        q.setParameter("id", user.getId());
        List l = q.list();
        String old_username = l.get(0).toString();

        String sqlQuery = "update User_H set password = :password, login = :login where id = :id";
        q = hib_session.createQuery(sqlQuery);

        if("".equals(user.getUsername()) & !"".equals(user.getPassword())){
           q.setParameter("password", passwordEncoder.encode(user.getPassword()));
        }
        if(!"".equals(user.getUsername()) & "".equals(user.getPassword())){
            q.setParameter("login", user.getUsername());
        }
        if(!"".equals(user.getUsername()) & !"".equals(user.getPassword())){
            q.setParameter("password", passwordEncoder.encode(user.getPassword()));
            q.setParameter("login", user.getUsername());
         }
        q.setParameter("id", user.getId());
        hib_session.getTransaction().commit();

        if(! "".equals(user.getUsername()) ){
            hib_session.beginTransaction();
            str = "delete from User_Roles_H where username = :oldusername";
            q = hib_session.createQuery(str);
            q.setParameter("oldusername", old_username);
            hib_session.getTransaction().commit();

            hib_session.beginTransaction();
            User_Roles_H newRole = new User_Roles_H(user.getUsername(), "ROLE_USER");
            hib_session.save(newRole);
            hib_session.getTransaction().commit();

            session.setAttribute("username", user.getUsername());
            session.setAttribute("user", user);
        }

        HibernateUtil.shutdown();

            return true;

        }

    @Override
    public boolean delete(String id) throws UserDAOException {
        return false;
    }

    @Override
    public String create(User_H entity) throws UserDAOException {
        return null;
    }

    @Override
    public String getNextNewId() throws UserDAOException {
        return null;
    }
}
