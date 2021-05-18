package com.project2.demo.keycloak.service;

import lombok.Value;

@Value
public class TokenRequest {
    String username;
    String password;

    public static TokenRequest create(String username, String password){
        return new TokenRequest(username, password);
    }


}
