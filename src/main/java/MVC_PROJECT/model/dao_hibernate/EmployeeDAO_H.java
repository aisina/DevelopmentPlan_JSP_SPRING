package MVC_PROJECT.model.dao_hibernate;

import MVC_PROJECT.controller.db.HibernateUtil;
import MVC_PROJECT.model.dao.EmployeeListDAO;
import MVC_PROJECT.model.exceptions.EmployeeDAOException;
import MVC_PROJECT.model.pojo_hibernate.Employee_H;
import MVC_PROJECT.model.pojo_hibernate.User_H;
import MVC_PROJECT.model.pojo_hibernate.User_Roles_H;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

/**
 * Created by innopolis on 17.01.2017.
 */
public class EmployeeDAO_H extends AbstractEmployeeListDAO<Employee_H, String>{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeListDAO.class);

    PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Map<String, Employee_H> getAll() throws EmployeeDAOException{

        Map<String, Employee_H> employeeList = new HashMap<String, Employee_H>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String str = "from Employee_H";
        Query q = session.createQuery(str);
        List lst = q.list();
        int i = 0;
        while(i < lst.size()){
            Employee_H employee = (Employee_H) lst.get(i);
            employeeList.put(employee.getId(), employee);
            i++;
        }

        HibernateUtil.shutdown();

        return employeeList;
    }

    @Override
    public List<Employee_H> getAllList() throws EmployeeDAOException {
        List<Employee_H> employeeList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String str = "from Employee_H";
        Query q = session.createQuery(str);
        List lst = q.list();
        int i = 0;
        while(i < lst.size()){
            Employee_H employee = (Employee_H) lst.get(i);
            employeeList.add(employee);
            i++;
        }

        HibernateUtil.shutdown();

        return employeeList;
    }


    @Override
    public Employee_H getEntityById(String key) throws EmployeeDAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String str = "from Employee_H where id = :key";
        Query q = session.createQuery(str);
        q.setParameter("key", key);
        List lst = q.list();
        Employee_H employee = (Employee_H) lst.get(0);
        HibernateUtil.shutdown();
        return employee;

    }

    @Override
    public boolean update(Employee_H entity) throws EmployeeDAOException {
        return false;
    }

    @Override
    public boolean delete(String id) throws EmployeeDAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String str = "from User_H where id = :userid";
        Query q = session.createQuery(str);
        q.setParameter("userid", id);
        List l = q.list();
        User_H user = (User_H) l.get(0);
        String username = user.getUsername();

        str = "delete from User_Roles_H WHERE username = :username";
        q = session.createQuery(str);
        q.setParameter("username", username);
        q.executeUpdate();
        session.getTransaction().commit();

        session.beginTransaction();
        str = "delete from Employee_H WHERE id = :id";
        q = session.createQuery(str);
        q.setParameter("id", id);
        q.executeUpdate();
        session.getTransaction().commit();

        session.beginTransaction();
        str = "delete from User_H WHERE id = :id";
        q = session.createQuery(str);
        q.setParameter("id", id);
        q.executeUpdate();
        session.getTransaction().commit();

        HibernateUtil.shutdown();

        return true;
    }

    @Override
    public String create(Employee_H employee) throws EmployeeDAOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String ids = getNextNewId();
        System.out.println("ids="+ids);
        String pass = passwordEncoder.encode(ids);
        System.out.println("pass="+pass);

        employee.setId(ids);
        session.save(employee);
        session.getTransaction().commit();


        User_H user = new User_H(ids, ids, "", pass,
                "", "");
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        User_Roles_H user_role = new User_Roles_H(ids, "ROLE_USER");
        session.beginTransaction();
        session.save(user_role);
        session.getTransaction().commit();

        HibernateUtil.shutdown();

        return ids;
    }

    @Override
    public String getNextNewId() throws EmployeeDAOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String str = "SELECT MAX(id) as id FROM Employee_H";
        Query q = session.createQuery(str);
        List l = q.list();

        Integer id = Integer.parseInt(l.get(0).toString()) + 1;
        return id.toString();

    }





    public static void main(String[] args) throws EmployeeDAOException {

        //session.save(employee);
        //session.delete(employee);

        /*session.getTransaction().commit();
        HibernateUtil.shutdown();*/

    }
}
