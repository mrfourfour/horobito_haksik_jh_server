package com.project2.demo.user.service;


import com.project2.demo.keycloak.service.Token;
import com.project2.demo.keycloak.service.TokenProvider;
import com.project2.demo.keycloak.service.TokenRequest;
import com.project2.demo.user.domain.User;
import com.project2.demo.user.domain.UserRepository;
import com.project2.demo.user.domain.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    /*
    TokenProvider 는 Interface
    이 인터페이스는 KeyCloak 패키지의
    인프라 층의 KeyCloakTokenProvider 라는 구현체에서 구현
     */
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;


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

    @Transactional
    public void save(String username) {
        userRepository.save(User.create(Username.create(username)));
    }
}
