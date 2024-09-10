package org.testboard.plyma_backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.testboard.plyma_backend.domain.user.domain.RefreshToken;
import org.testboard.plyma_backend.domain.user.domain.User;
import org.testboard.plyma_backend.domain.user.domain.repository.RefreshTokenRepository;
import org.testboard.plyma_backend.domain.user.domain.repository.UserRepository;
import org.testboard.plyma_backend.domain.user.exception.UserIdAlreadyExistException;
import org.testboard.plyma_backend.domain.user.presentation.dto.SingUpRequest;
import org.testboard.plyma_backend.domain.user.presentation.dto.TokenResponse;
import org.testboard.plyma_backend.global.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse userSingUp(SingUpRequest request){
        if(userRepository.existsByUserId(request.getUserName())){
            throw new UserIdAlreadyExistException();
        }

        User user = userRepository.save(User.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getUserName())
                .classNum(request.getClassNum())
                .build());

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getUserId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .userId(user.getUserId())
                        .refreshToken(jwtTokenProvider.generateRefreshToken(user.getUserId()))
                        .build()).getRefreshToken())
                .build();
    }
}
