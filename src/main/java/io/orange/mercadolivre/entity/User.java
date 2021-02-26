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
        try {
            this.password = encryptPass(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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

    //Encrypt password
    public String encryptPass(String password) throws NoSuchAlgorithmException {
        MessageDigest safeBox = MessageDigest.getInstance("MD5");
        safeBox.update(password.getBytes(),0,password.length());
        return new BigInteger(1,safeBox.digest()).toString(16);
    }
    //End encrypt password


    //ToString Block---------------------------------
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + username + '\'' +
                ", password='" + password + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}

