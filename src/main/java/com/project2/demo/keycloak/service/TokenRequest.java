package com.project2.demo.keycloak.service;

import lombok.Value;

@Value
public class TokenRequest {
    String username;
    String password;
}
