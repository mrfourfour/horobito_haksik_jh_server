package com.project2.demo.user.controller;


import com.project2.demo.keycloak.service.Token;
import com.project2.demo.keycloak.service.TokenProvider;
import com.project2.demo.user.service.LoginParameter;
import com.project2.demo.user.service.SingUpParameter;
import com.project2.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
        return tokenProvider.refresh(refreshTokenPayload.refreshToken);
    }

    @PostMapping("/sign-up")
    public LoginResponse signUp(@RequestBody SignUpRequest signUpRequest){
        Token token = userService.signUp(signUpRequest.to());
        return new LoginResponse(token);
    }


}

class LoginRequest{
    String username;
    String password;

    LoginParameter to(){
        return new LoginParameter(username, password);
    }


}

@Value
class RefreshTokenPayload{
    String refreshToken;
}

@Value
class SignUpRequest{
    String username;
    String password;

    SingUpParameter to(){
        return new SingUpParameter(username, password);
    }

}

@Value
class LoginResponse{
    Token token = null;

    pu
}
