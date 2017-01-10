package MVC_PROJECT.service;

import MVC_PROJECT.model.Employee;
import MVC_PROJECT.model.dao.AbstractDAO;
import MVC_PROJECT.model.dao.EmployeeListDAO;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by innopolis on 09.01.2017.
 */
public class EmployeeService {

    AbstractDAO emplDAO = new EmployeeListDAO();

    public ModelAndView addEmployee(Employee employee){
        ModelAndView mav = new ModelAndView();
        //случай, когда поля для ввода пусты
        if("".equals(employee.getName()) || "".equals(employee.getPosition()) || "".equals(employee.getDepartment()) || "".equals(employee.getEmail())){
            mav.addObject("employeeList", emplDAO.getAllList());
            //без ссобщения
            mav.setViewName("addEmployee");
        }else {
            String ids = "";
            ids = emplDAO.create(employee);
            if (!"".equals(ids)) {
                mav.addObject("employeeList", emplDAO.getAllList());
                mav.setViewName("employeeList");
            } else {
                mav.setViewName("employeeList"); //по сути, здесь должна быть страница ошибки
            }

        }
        return mav;
    }

    public String getNextNewId(){
        return emplDAO.getNextNewId();
    }


    public ModelAndView deleteEmployee(String id){
        boolean bool = emplDAO.delete(id);
        ModelAndView mav = new ModelAndView();
        if(bool){
            mav.addObject("employeeList", emplDAO.getAllList());
            mav.setViewName("employeeList");
        }else{
            mav.setViewName("employeeList"); //по сути, здесь должна быть страница ошибки
        }
        return mav;
    }

    public ModelAndView showEmplList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("employeeList");
        mav.addObject("employeeList", emplDAO.getAllList());
        return mav;
    }
}
