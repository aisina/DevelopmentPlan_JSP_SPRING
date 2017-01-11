import MVC_PROJECT.model.Employee;
import MVC_PROJECT.model.dao.AbstractEmployeeListDAO;
import MVC_PROJECT.model.exceptions.EmployeeDAOException;
import MVC_PROJECT.service.EmployeeService;
import MVC_PROJECT.service.IEmployeeService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by innopolis on 11.01.2017.
 */
public class EmployeeServiceTest {

    private static IEmployeeService emplService;
    private AbstractEmployeeListDAO emplDAO;

    @Before
    public void before(){
        emplService = new EmployeeService(emplDAO);
    }

    @Test
    public void addEmployee() throws EmployeeDAOException {
        Employee employee = new Employee();
        employee.setName("");
        employee.setPosition("");
        employee.setDepartment("");
        employee.setEmail("");

        boolean isAdded = emplService.addEmployee(employee);
        assertTrue("Не все поля заполнены, не добавлен", isAdded == false);
    }
}
