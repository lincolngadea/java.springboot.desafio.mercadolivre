package io.orange.mercadolivre.config.security.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDto {
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.username,
                this.password);
    }
}
