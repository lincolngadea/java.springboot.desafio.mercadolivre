package io.orange.mercadolivre.request;

import io.orange.mercadolivre.entity.User;
import io.orange.mercadolivre.validators.annotations.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewUserRequest {

    @NotBlank
    @Email
    @UniqueValue(fieldName = "username", domainClass = User.class)
    private String username;
    @NotBlank
    @Size(min = 6)
    private String password;

    //Default Builder for Framework use
    @Deprecated
    public NewUserRequest(){}

    //Start Builder
    public NewUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public NewUserRequest(User user) {
        this.username = user.getUsername();
    }
    //End Builder


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //Start User entity convert
    public User toModel(){
        return new User(this.username,this.password);
    }
    //End entity convert

}
