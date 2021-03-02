package io.orange.mercadolivre.config.security.dto;

public class AuthenticationTokenOutputDto {

    private String tokenType;
    private String token;

    public AuthenticationTokenOutputDto(String tokenType, String token) {
        super();
        this.tokenType = tokenType;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }
}
