package org.testboard.plyma_backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.testboard.plyma_backend.domain.user.domain.RefreshToken;
import org.testboard.plyma_backend.domain.user.domain.repository.RefreshTokenRepository;
import org.testboard.plyma_backend.domain.user.exception.RefreshTokenNotFound;
import org.testboard.plyma_backend.domain.user.service.util.UserUtil;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private RefreshTokenRepository refreshTokenRepository;
    private UserUtil userUtil;

    public void logout(){
        RefreshToken refreshToken = refreshTokenRepository.findById(userUtil.getUserId()).orElseThrow(() -> RefreshTokenNotFound.EXCEPTION);
        refreshTokenRepository.delete(refreshToken);
    }
}
