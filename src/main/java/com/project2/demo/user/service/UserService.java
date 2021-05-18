package com.project2.demo.user.service;


import com.project2.demo.keycloak.service.Token;
import com.project2.demo.keycloak.service.TokenProvider;
import com.project2.demo.keycloak.service.TokenRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenProvider tokenProvider;


    public Token login(LoginParameter loginParameter){
        TokenRequest tokenRequest
                = TokenRequest.create(loginParameter.getUsername()
                ,loginParameter.getPassword());
        return tokenProvider.issue(tokenRequest);
    }

    public UserDto findLoggedUser(){
        UserDto userDto = null;

        return userDto;
    }
}
