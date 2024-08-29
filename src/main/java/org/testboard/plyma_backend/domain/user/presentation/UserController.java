package org.testboard.plyma_backend.domain.user.presentation;


import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.testboard.plyma_backend.domain.user.presentation.dto.LogInRequest;
import org.testboard.plyma_backend.domain.user.presentation.dto.SingUpRequest;
import org.testboard.plyma_backend.domain.user.presentation.dto.TokenResponse;
import org.testboard.plyma_backend.domain.user.presentation.dto.UserDetailResponse;
import org.testboard.plyma_backend.domain.user.service.LoginService;
import org.testboard.plyma_backend.domain.user.service.LogoutService;
import org.testboard.plyma_backend.domain.user.service.ReassignTokenService;
import org.testboard.plyma_backend.domain.user.service.SignupService;

@RestController
@RequestMapping("/user")
@Transactional
@RequiredArgsConstructor
public class UserController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final ReassignTokenService reassignTokenService;

    @PostMapping(value = "/singup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse singUp(@RequestBody SingUpRequest request) {
        return signupService.userSingUp(request);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse logIn(@RequestBody LogInRequest request) {
        return loginService.userLogIn(request);
    }

    @PostMapping(value = "/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse reassignToken(@RequestHeader("Refresh-Token") String refreshToken) {
        return reassignTokenService.reasSignToken(refreshToken);
    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {
        logoutService.logout();
    }


}