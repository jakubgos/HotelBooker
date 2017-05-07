package com.jgos.hotelbooker.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserDb {

    @Id
    public Long id;
    @Column
    public String email;
    @Column
    public String password;

    @ElementCollection
    public List<String> authorities = new ArrayList<>();


    public UserDb() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
   }
}
