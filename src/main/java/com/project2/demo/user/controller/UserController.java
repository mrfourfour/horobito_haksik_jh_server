package com.project2.demo.user.controller;


import com.project2.demo.keycloak.service.Token;
import com.project2.demo.keycloak.service.TokenProvider;
import com.project2.demo.user.service.LoginParameter;
import com.project2.demo.user.service.SignUpParameter;
import com.project2.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final TokenProvider tokenProvider;


    /*
    핵심
    로그인을 했을 때 토큰을 준다.
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        Token token = userService.login(loginRequest.to());
        return new LoginResponse(token);

    }


    /*
     용도
    자동 로그인 + 15분 지났을 때 재인증

     */
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
    public String refreshToken;
}

@Value
class SignUpRequest{
    String username;
    String password;

    SignUpParameter to(){
        return new SignUpParameter(username, password);
    }

}

@Value
class LoginResponse{
    public Token token ;

    public LoginResponse(Token token) {
        this.token = token;
    }
}
