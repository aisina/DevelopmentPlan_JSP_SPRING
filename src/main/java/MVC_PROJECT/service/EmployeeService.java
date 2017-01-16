package MVC_PROJECT.service;

import MVC_PROJECT.model.Employee;
import MVC_PROJECT.model.dao.AbstractEmployeeListDAO;
import MVC_PROJECT.model.exceptions.EmployeeDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by innopolis on 09.01.2017.
 */

@Service
public class EmployeeService implements IEmployeeService{

    private final AbstractEmployeeListDAO emplDAO;

    @Autowired
    public EmployeeService(AbstractEmployeeListDAO emplDAO) {
        this.emplDAO = emplDAO;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public boolean addEmployee(Employee employee) throws EmployeeDAOException {
        boolean bool = true;
        //случай, когда поля для ввода пусты
        if("".equals(employee.getName()) || "".equals(employee.getPosition()) || "".equals(employee.getDepartment()) || "".equals(employee.getEmail())){
            bool = false;
        }else {
            String ids = "";
            ids = emplDAO.create(employee);
            if ("".equals(ids)) {
                bool = false;
            }
        }
        return bool;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public String getNextNewId() throws EmployeeDAOException {
        return emplDAO.getNextNewId();
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<Employee> deleteEmployee(String id) throws EmployeeDAOException {
        boolean bool = emplDAO.delete(id);
        if(bool){
            return emplDAO.getAllList();
        }else{
            return null;
        }
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<Employee> showEmplList() throws EmployeeDAOException {
        return emplDAO.getAllList();
    }
}
