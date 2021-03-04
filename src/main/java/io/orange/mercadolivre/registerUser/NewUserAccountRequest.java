package io.orange.mercadolivre.registerUser;

import io.orange.mercadolivre.config.validators.annotations.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewUserAccountRequest {

    @NotBlank
    @Email
    @UniqueValue(fieldName = "username", domainClass = UserAccount.class, message = "Não foi possível realizar um cadastro com esse email")
    private String username;
    @NotBlank
    @Size(min = 6)
    private String password;

    //Default Builder for Framework use
    @Deprecated
    public NewUserAccountRequest() {
    }

    //Start Builder
    public NewUserAccountRequest(@NotBlank @Email String username, @NotBlank @Size(min = 6) String password) {

        this.username = username;
        this.password = password;
    }

    public NewUserAccountRequest(UserAccount user) {
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
    public UserAccount toModel() {
        return new UserAccount(this.username, this.password);
    }
    //End entity convert

}
