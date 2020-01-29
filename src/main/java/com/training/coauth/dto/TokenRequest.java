package com.training.coauth.dto;

import javax.validation.constraints.NotBlank;

public class TokenRequest {

    @NotBlank
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
