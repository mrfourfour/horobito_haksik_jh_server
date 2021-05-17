package com.project2.demo.user.controller;


import com.project2.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        Token token = userService.login(loginRequest.to());
        return LoginResponse(token);

    }


    @PostMapping("/token/refresh")
    public Token refreshToken(@RequestBody RefreshTokenPayload refreshTokenPayload){
        return refresh(refreshTokenPayload);
    }

    private Token refresh(RefreshTokenPayload refreshTokenPayload){
        tokenProvider.refresh(refreshTokenPayload.refreshToken);
    }

    @PostMapping("/sign-up")
    public LoginResponse signUp(@RequestBody SignUpRequest signUpRequest){
        Token token = userService.signUp(signUpRequest.to());
        return LoginResponse(token);
    }


}

class LoginRequest{
    String username;
    String password;

:

}
