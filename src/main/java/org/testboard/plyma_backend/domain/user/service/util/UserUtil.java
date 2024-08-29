package org.testboard.plyma_backend.domain.user.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.testboard.plyma_backend.domain.user.domain.User;
import org.testboard.plyma_backend.domain.user.domain.repository.UserRepository;
import org.testboard.plyma_backend.domain.user.exception.UserNotFoundException;
import org.testboard.plyma_backend.global.jwt.exception.TokenUnauthorizedException;

@Component//bean
@RequiredArgsConstructor
public class UserUtil {
    private final UserRepository userRepository;

    public String getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) throw TokenUnauthorizedException.EXCEPTION;
        return authentication.getName();
    }

    public User getUser(){
        return userRepository.findById(getUserId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }


}
