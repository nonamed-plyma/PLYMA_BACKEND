package org.testboard.plyma_backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.testboard.plyma_backend.domain.user.domain.RefreshToken;
import org.testboard.plyma_backend.domain.user.domain.User;
import org.testboard.plyma_backend.domain.user.domain.repository.RefreshTokenRepository;
import org.testboard.plyma_backend.domain.user.domain.repository.UserRepository;
import org.testboard.plyma_backend.domain.user.exception.PasswordMismatchException;
import org.testboard.plyma_backend.domain.user.exception.UserNotFoundException;
import org.testboard.plyma_backend.domain.user.presentation.dto.LogInRequest;
import org.testboard.plyma_backend.domain.user.presentation.dto.TokenResponse;
import org.testboard.plyma_backend.global.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse userLogIn(LogInRequest request){
        User user =userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw PasswordMismatchException.EXCEPTION;
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(request.getUserId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .userId(user.getUserId())
                        .refreshToken(jwtTokenProvider.generateRefreshToken(user.getUserId()))
                        .build()).getRefreshToken())
                .build();
    }
}