package MVC_PROJECT.controller;

import MVC_PROJECT.model.Employee;
import MVC_PROJECT.controller.mail.MailSender;
import MVC_PROJECT.model.dao.AbstractDAO;
import MVC_PROJECT.model.dao.EmployeeListDAO;
import MVC_PROJECT.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by innopolis on 05.01.2017.
 */

//add. delete. select and etc.
@Controller
public class EmployeeController {

    EmployeeService emplService = new EmployeeService();

    @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.GET)
    public ModelAndView showAddEmployeePage() {
        return new ModelAndView("addEmployee", "addNewEmployee", new Employee());
    }


    //Аннотацией @RequestMapping(value = "/simple1") сообщаем,
    //что данный контроллер будет обрабатывать запрос, URI которого "/simple1"
    @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("addNewEmployee") Employee myEmployee){

        ModelAndView mav = emplService.addEmployee(myEmployee);
        String ids = emplService.getNextNewId();

        String message = "Здравствуйте, " + myEmployee.getName() + "! \n";
        message += "Вам предоставлен доступ к системе профессионального развития сотрудников компании. \n";
        message += "Данные для входа в личный кабинет:\n";
        message += "Логин: " + ids + "\n";
        message += "Пароль: " + ids + "\n";
        message += "Вы можете изменить данные в личном кабинете.";
        String subject = "Вам предоставлен доступ к системе профразвития сотрудников.";
        MailSender.sendMail(message, subject, myEmployee.getEmail());

        return mav;
    }


    @RequestMapping(value = "/showEmployeeList", method = RequestMethod.GET)
    public ModelAndView showEmplList(){
        return emplService.showEmplList();
    }


    @RequestMapping(value = "/deleteEmployee")
    public ModelAndView deleteEmployee(HttpServletRequest request){
        return emplService.deleteEmployee(request.getParameter("employeeID"));
    }
}
