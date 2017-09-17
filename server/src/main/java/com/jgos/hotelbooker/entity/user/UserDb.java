package com.jgos.hotelbooker.entity.user;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserDb {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

    @ElementCollection
    private List<String> authorities = new ArrayList<>();

    public UserDb(String email, String password, List<String> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

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


    @Override
    public String toString() {
        return "UserDb{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
