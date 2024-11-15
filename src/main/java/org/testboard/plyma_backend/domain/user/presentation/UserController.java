package org.testboard.plyma_backend.domain.user.presentation;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.testboard.plyma_backend.domain.user.presentation.dto.LogInRequest;
import org.testboard.plyma_backend.domain.user.presentation.dto.SignUpRequest;
import org.testboard.plyma_backend.domain.user.presentation.dto.TokenResponse;
import org.testboard.plyma_backend.domain.user.presentation.dto.UserDetailResponse;
import org.testboard.plyma_backend.domain.user.service.*;

@RestController
@RequestMapping("/user")
@Transactional
@RequiredArgsConstructor
public class UserController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final UserService userService;
    private final ReassignTokenService reassignTokenService;
    public final UserService userService;

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signUp(@RequestBody SignUpRequest request) {
        return signupService.userSingUp(request);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse logIn(@RequestBody LogInRequest request) {
        return loginService.userLogIn(request);
    }

    @PostMapping(value = "/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse reasSignToken(@RequestHeader("Refresh-Token") String refreshToken) {
        return reassignTokenService.ReasSignToken(refreshToken);
    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {
        logoutService.logout();
    }

    @GetMapping
    public UserDetailResponse getUser(){return userService.getUser();}

    @GetMapping("/{userId}")
    public void existsUserId(@PathVariable String userId){userService.existsUserId(userId);}
}
