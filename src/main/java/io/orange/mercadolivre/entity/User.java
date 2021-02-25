package io.orange.mercadolivre.entity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class User {
    private Long id;
    private String login;
    private String password;
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Start Builder
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    //Constructor End



    //ToString Block
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

