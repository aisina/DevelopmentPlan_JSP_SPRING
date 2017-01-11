package MVC_PROJECT.model.dao;

import MVC_PROJECT.controller.GeneratePassword;
import MVC_PROJECT.model.User;
import MVC_PROJECT.controller.db.DatabaseConnection;
import MVC_PROJECT.model.Employee;
import MVC_PROJECT.model.exceptions.EmployeeDAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by innopolis on 27.12.2016.
 */
@Repository
public class EmployeeListDAO extends AbstractEmployeeListDAO<Employee, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeListDAO.class);

    @Override
    public Map<String, Employee> getAll() throws EmployeeDAOException{
        try(Connection connection = DatabaseConnection.getConnection();
            Statement stmt = connection.createStatement();) {

            Map<String, Employee> employeeList = new HashMap<String, Employee>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getString("id"));
                employee.setName(rs.getString("name"));
                employee.setDepartment(rs.getString("department"));
                employee.setPosition(rs.getString("position"));
                employee.setEmail(rs.getString("email"));
                employeeList.put(employee.getId(), employee);
            }
            return employeeList;

        } catch (SQLException e) {
            EmployeeListDAO.LOGGER.warn(e.getMessage());
            throw new EmployeeDAOException();
        }
    }


    @Override
    public List<Employee> getAllList() throws EmployeeDAOException{
        try(Connection connection = DatabaseConnection.getConnection();
            Statement stmt = connection.createStatement();) {

            List<Employee> employeeList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getString("id"));
                employee.setName(rs.getString("name"));
                employee.setDepartment(rs.getString("department"));
                employee.setPosition(rs.getString("position"));
                employee.setEmail(rs.getString("email"));
                employeeList.add(employee);
            }
            EmployeeListDAO.LOGGER.info("EmployeeList");
            return employeeList;

        } catch (SQLException e) {
            EmployeeListDAO.LOGGER.warn(e.getMessage());
            throw new EmployeeDAOException();
        }
    }

    @Override
    public Employee getEntityById(String key) throws EmployeeDAOException{
        Employee employee = new Employee();
        Connection connection = DatabaseConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE WHERE ID='" + key + "'");
            while(rs.next()){
                employee.setId(rs.getString("id"));
                employee.setName(rs.getString("name"));
                employee.setDepartment(rs.getString("department"));
                employee.setPosition(rs.getString("position"));
                employee.setEmail(rs.getString("email"));
            }
            return employee;

        } catch (SQLException e) {
            EmployeeListDAO.LOGGER.warn(e.getMessage());
            throw new EmployeeDAOException();
        }
        //return employee;
    }

    @Override
    public boolean update(Employee entity) throws EmployeeDAOException {
        return false;
    }

    @Override
    public boolean delete(String id) throws EmployeeDAOException{
        try(Connection connection = DatabaseConnection.getConnection()) {

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM EMPLOYEE WHERE ID=" + id);
            stmt.executeUpdate("DELETE FROM USERS WHERE ID=" + id);
            return true;

        } catch (SQLException e) {
            EmployeeListDAO.LOGGER.warn(e.getMessage());
            throw new EmployeeDAOException();
        }
    }

    @Override
    public String getNextNewId() throws EmployeeDAOException{
        Connection connection = DatabaseConnection.getConnection();
        String ids = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(ID) maxid FROM EMPLOYEE");

            while (rs.next()) {
                ids = rs.getString("maxid");
            }
            int id = Integer.parseInt(ids) + 1;
            ids = "" + id;
            return ids;
        } catch (SQLException e) {
            EmployeeListDAO.LOGGER.warn(e.getMessage());
            throw new EmployeeDAOException();
        }

    }

    @Override
    public String create(Employee employee) throws EmployeeDAOException{
        Connection connection = DatabaseConnection.getConnection();
        String ids = "";
        try {
            Statement stmt = connection.createStatement();

            ids = getNextNewId();
            int id = Integer.parseInt(ids);

            String sql = "INSERT INTO EMPLOYEE VALUES('" + id + "', '" + employee.getName() + "', '" + employee.getDepartment() + "', '" + employee.getPosition() + "', '" + employee.getEmail() + "')";

            stmt.executeUpdate(sql);

            String salt = "testsaltconstant";
            User user = new User();
            user.setSalt(salt);
            String pass = GeneratePassword.generatePass(ids, user);
            stmt.executeUpdate("insert into users values('" + id + "', '" + id + "', '" + pass + "', '" + salt + "')");

            return ids;
        } catch (SQLException e) {
            EmployeeListDAO.LOGGER.warn(e.getMessage());
            throw  new EmployeeDAOException();
        }
    }
}
