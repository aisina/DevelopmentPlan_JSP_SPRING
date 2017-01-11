package MVC_PROJECT.controller;

import MVC_PROJECT.model.Employee;
import MVC_PROJECT.controller.mail.MailSender;
import MVC_PROJECT.model.exceptions.EmployeeDAOException;
import MVC_PROJECT.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by innopolis on 05.01.2017.
 */

@Controller
public class EmployeeController {

    private final IEmployeeService emplService;

    @Autowired
    public EmployeeController(IEmployeeService emplService) {
        this.emplService = emplService;
    }


    @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.GET)
    public ModelAndView showAddEmployeePage() {
        return new ModelAndView("addEmployee", "addNewEmployee", new Employee());
    }


    @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("addNewEmployee") Employee myEmployee){

        ModelAndView mav = new ModelAndView();
        boolean bool = false;
        try{
            bool = emplService.addEmployee(myEmployee);

            if(bool) {
                try {
                    mav.addObject("employeeList", emplService.showEmplList());
                    mav.setViewName("employeeList");

                    String ids = emplService.getNextNewId();

                    String message = "Здравствуйте, " + myEmployee.getName() + "! \n";
                    message += "Вам предоставлен доступ к системе профессионального развития сотрудников компании. \n";
                    message += "Данные для входа в личный кабинет:\n";
                    message += "Логин: " + ids + "\n";
                    message += "Пароль: " + ids + "\n";
                    message += "Вы можете изменить данные в личном кабинете.";
                    String subject = "Вам предоставлен доступ к системе профразвития сотрудников.";
                    MailSender.sendMail(message, subject, myEmployee.getEmail());
                }
                catch(EmployeeDAOException e){
                    mav.setViewName("employeeList");
                }
            }
            else{
                mav.setViewName("addEmployee");
            }

        }catch(EmployeeDAOException e){
            mav.setViewName("employeeList");
        }

        return mav;
    }


    @RequestMapping(value = "/showEmployeeList", method = RequestMethod.GET)
    public ModelAndView showEmplList(){
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("employeeList", emplService.showEmplList());
            mav.setViewName("employeeList");
        } catch (EmployeeDAOException e) {
            mav.setViewName("errorPage");
        }
        return mav;
    }


    @RequestMapping(value = "/deleteEmployee")
    public ModelAndView deleteEmployee(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("employeeList", emplService.deleteEmployee(request.getParameter("employeeID")));
            mav.setViewName("employeeList");
        } catch (EmployeeDAOException e) {
            mav.setViewName("errorPage");
        }

        return mav;
    }
}
