package io.orange.mercadolivre.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;

    @NotNull
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Default builder for framework use.
    public User(){}

    //Start Builder----------------------------------
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    //Constructor End---------------------------------

    //Bock Getters------------------------------------
    public Long getId() { return id; }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    //End Block Getters




    //ToString Block---------------------------------
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}

