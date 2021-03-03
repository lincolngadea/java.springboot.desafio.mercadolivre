package io.orange.mercadolivre.utils;

public class GenterateToken {
    private String token;

    public String getToken() {
        return token;
    }

    public GenterateToken toToken(String token) {
        this.token = token;
        return new TokenGenarate(this.token);
    }


}
