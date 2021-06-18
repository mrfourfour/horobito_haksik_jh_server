package com.project2.demo.keycloak.service;

import lombok.Value;

@Value
public class TokenRequest {
    String email;
    String password;

    public static TokenRequest create(String email, String password){
        return new TokenRequest(email, password);
    }


}
