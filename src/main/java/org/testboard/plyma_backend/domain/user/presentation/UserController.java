package org.testboard.plyma_backend.domain.user.presentation;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.testboard.plyma_backend.domain.user.presentation.dto.LogInRequest;
import org.testboard.plyma_backend.domain.user.presentation.dto.SingUpRequest;
import org.testboard.plyma_backend.domain.user.presentation.dto.TokenResponse;
import org.testboard.plyma_backend.domain.user.service.LoginService;
import org.testboard.plyma_backend.domain.user.service.ReassignTokenService;
import org.testboard.plyma_backend.domain.user.service.SignupService;

@RestController
@RequestMapping("/user")
@Transactional
@RequiredArgsConstructor
public class UserController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final ReassignTokenService reassignTokenService;

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse singUp(@RequestBody SingUpRequest request) {return signupService.userSingUp(request);}

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse logIn(@RequestBody LogInRequest request){return loginService.userLogIn(request);}

    @PostMapping(value = "/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse reassignToken(@RequestHeader("Refresh-Token") String refreshToken) {return reassignTokenService.reasSignToken(refreshToken);}


}
