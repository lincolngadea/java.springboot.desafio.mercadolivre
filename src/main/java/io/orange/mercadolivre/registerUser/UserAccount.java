package io.orange.mercadolivre.registerUser;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "useraccount")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotNull
    private LocalDateTime localDateTime = LocalDateTime.now();


    //Default builder for framework use.
    public UserAccount() {
    }

    //Start Builder----------------------------------

    /**
     * @param username is string in email format
     * @param password is string in clear text
     */
    public UserAccount(@NotBlank String username, @NotBlank @Size(min = 6) String password) {

        Assert.isTrue(StringUtils.hasLength(username), "email cannot be blanck");
        Assert.isTrue(StringUtils.hasLength(password), "password cannot be blanck");
        Assert.isTrue(password.length() >= 6, "password must have at last 6 characters");

        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
    }
    //Constructor End---------------------------------


    //Bock Getters------------------------------------
    public Long getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    //End Block Getters

    //ToString Block---------------------------------
    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", login='" + username + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

}

