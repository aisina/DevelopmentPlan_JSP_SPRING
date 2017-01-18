package MVC_PROJECT.model.pojo_hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by innopolis on 18.01.2017.
 */
@Entity
@Table(name="USER_ROLES")
public class User_Roles_H {

    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ROLE")
    private String role;

    public User_Roles_H() {
    }

    public User_Roles_H(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
