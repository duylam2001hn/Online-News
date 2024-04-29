package io.spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user")
public class User extends Common{


    @NotEmpty(message = "Email không được để trống")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password không được để trống")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private int role;


    @Column(name = "status")
    private int status;


    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
