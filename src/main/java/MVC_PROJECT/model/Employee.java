package MVC_PROJECT.model;

/**
 * Created by innopolis on 26.12.2016.
 */

public class Employee {

    private String id;
    private String name;
    private String department;
    private String position;
    private String email;

    public Employee() {
    }

    public Employee(String id) {
        this.id = id;
    }

    public Employee(String name, String department, String position, String email) {
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
