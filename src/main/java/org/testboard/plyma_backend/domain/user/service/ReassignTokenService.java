package org.testboard.plyma_backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.testboard.plyma_backend.domain.user.exception.UserNotFoundException;
import org.testboard.plyma_backend.domain.user.presentation.dto.TokenResponse;
import org.testboard.plyma_backend.global.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class ReassignTokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public TokenResponse ReasSignToken(String refreshToken){
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw UserNotFoundException.EXCEPTION;
        }
        String userId = jwtTokenProvider.getSubject(refreshToken);
        UserDetails userDetails;
        try{
            userDetails = userDetailsService.loadUserByUsername(userId);
        }catch (UserNotFoundException exception){
            throw UserNotFoundException.EXCEPTION;
        }
        String newAccessToken = jwtTokenProvider.generateAccessToken(userId);
        TokenResponse response = TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();

        return response;
    }
}
