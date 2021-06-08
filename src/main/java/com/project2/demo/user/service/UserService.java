package com.project2.demo.user.service;


import com.project2.demo.keycloak.service.Token;
import com.project2.demo.keycloak.service.TokenProvider;
import com.project2.demo.keycloak.service.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenProvider tokenProvider;


    /*
    기능
    토큰 공급자로부터 토큰을 요청하고,
    받은 토큰을 발급하라
     */
    public Token login(LoginParameter loginParameter){
        TokenRequest tokenRequest
                = TokenRequest.create(loginParameter.getUsername()
                ,loginParameter.getPassword());
        return tokenProvider.issue(tokenRequest);
    }

    public Token signUp(SignUpParameter signUpParameter){
        TokenRequest tokenRequest
                = TokenRequest.create(signUpParameter.getUsername(),
                signUpParameter.getPassword());
        tokenProvider.signUp(tokenRequest);
        return tokenProvider.issue(tokenRequest);
    }

    public UserDto findLoggedUser(){
        UserDto userDto = null;

        return userDto;
    }
}
