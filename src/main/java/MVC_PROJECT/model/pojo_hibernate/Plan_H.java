package MVC_PROJECT.model.pojo_hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by innopolis on 18.01.2017.
 */
@Entity
@Table(name="PLAN")
public class Plan_H {

    @Id
    @Column(name="YEAR")
    private String year;

    @Column(name="EMPL_NAME")
    private String employeeName;

    @Column(name="POSITION")
    private String employeePosition;

    @Column(name="PLAN_TYPE")
    private String planType;

    @Column(name="EMPLOYEE_ID")
    private String employeeId;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
