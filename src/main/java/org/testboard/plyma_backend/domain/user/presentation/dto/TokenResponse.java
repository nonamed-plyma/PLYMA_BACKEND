package org.testboard.plyma_backend.domain.user.presentation.dto;

import lombok.Builder;

public class TokenResponse {

    private final String accessToken;
    private final String refreshToken;

    @Builder
    public TokenResponse(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
