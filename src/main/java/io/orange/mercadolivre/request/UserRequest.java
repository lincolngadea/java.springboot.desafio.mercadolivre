package io.orange.mercadolivre.request;

import io.orange.mercadolivre.entity.User;

public class UserRequest {
    private String login;
    private String password;

    //Default Builder for Framework use
    @Deprecated
    public UserRequest(){}

    //Start Builder
    public UserRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
    //End Builder

    //Start User entity convert
    public User toModel(){
        return new User(this.login,this.password);
    }
    //End entity convert


}
