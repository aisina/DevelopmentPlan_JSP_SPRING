package MVC_PROJECT.model.pojo_hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by innopolis on 26.12.2016.
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee_H {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String name;

    @Column(name="DEPARTMENT")
    private String department;

    @Column(name="POSITION")
    private String position;

    @Column(name="EMAIL")
    private String email;


    public Employee_H() {
    }

    public Employee_H(String id) {
        this.id = id;
    }

    public Employee_H(String name, String department, String position, String email) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public static void main(String[] args) {
        System.out.println(Employee.class.getProtectionDomain().getCodeSource());
        SecurityManager security = System.getSecurityManager();
        System.out.println(security);
    }*/
}
