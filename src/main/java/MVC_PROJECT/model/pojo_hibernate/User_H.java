package MVC_PROJECT.model.pojo_hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by innopolis on 18.01.2017.
 */
@Entity
@Table(name="USERS")
public class User_H {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LOGIN")
    private String username;

    private String confirmUsername;

    @Column(name = "PASSWORD")
    private String password;

    private String confirmPassword;

    @Column(name = "SALT")
    private String salt;

    public User_H() {
    }

    public User_H(String id, String username) {
        this.id = id;
        this.username = username;
    }


    public User_H(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User_H(String id, String username, String confirmUsername, String password, String confirmPassword, String salt) {
        this.id = id;
        this.username = username;
        this.confirmUsername = confirmUsername;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getConfirmUsername() {
        return confirmUsername;
    }

    public void setConfirmUsername(String confirmUsername) {
        this.confirmUsername = confirmUsername;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
