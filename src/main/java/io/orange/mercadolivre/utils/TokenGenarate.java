package io.orange.mercadolivre.utils;

public class TokenGenarate extends GenterateToken {
    private String token;

    @Override
    public String getToken() {
        return token;
    }

    @Deprecated
    public TokenGenarate() {
    }

    public TokenGenarate(String token) {
        this.token = token;
    }
}
