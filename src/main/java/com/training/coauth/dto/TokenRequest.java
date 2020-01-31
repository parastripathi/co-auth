package com.training.coauth.dto;

import javax.validation.constraints.NotBlank;

public class TokenRequest {


    @NotBlank
    private Long provider;

    public Long getProvider() {
        return provider;
    }

    public void setProvider(Long provider) {
        this.provider = provider;
    }
}
