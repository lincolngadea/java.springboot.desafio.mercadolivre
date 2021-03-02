package io.orange.mercadolivre.model.request;

import io.orange.mercadolivre.config.validators.annotations.UniqueValue;
import io.orange.mercadolivre.model.UserLogin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewUserLoginRequest {

    @NotBlank
    @Email
    @UniqueValue(fieldName = "username", domainClass = UserLogin.class, message = "Não foi possível realizar um cadastro com esse email")
    private String username;
    @NotBlank
    @Size(min = 6)
    private String password;

    //Default Builder for Framework use
    @Deprecated
    public NewUserLoginRequest() {
    }

    //Start Builder
    public NewUserLoginRequest(@NotBlank @Email String username, @NotBlank @Size(min = 6) String password) {

        this.username = username;
        this.password = password;
    }

    public NewUserLoginRequest(UserLogin user) {
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
    public UserLogin toModel() {
        return new UserLogin(this.username, this.password);
    }
    //End entity convert

}
