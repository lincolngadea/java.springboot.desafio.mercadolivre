package io.orange.mercadolivre.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @NotNull
    private LocalDateTime localDateTime = LocalDateTime.now();

    //Default builder for framework use.
    public User(){}

    //Start Builder----------------------------------
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //Constructor End---------------------------------

    //Bock Getters------------------------------------
    public Long getId() { return id; }
    public String getUsername() {
        return username;
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
                ", login='" + username + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}

