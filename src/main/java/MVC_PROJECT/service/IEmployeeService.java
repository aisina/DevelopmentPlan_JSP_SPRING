package MVC_PROJECT.service;

import MVC_PROJECT.model.Employee;
import MVC_PROJECT.model.exceptions.EmployeeDAOException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by innopolis on 10.01.2017.
 */
public interface IEmployeeService {

    public boolean addEmployee(Employee employee) throws EmployeeDAOException;
    public String getNextNewId() throws EmployeeDAOException;
    public List<Employee> deleteEmployee(String id) throws EmployeeDAOException;
    public List<Employee> showEmplList() throws EmployeeDAOException;
}
