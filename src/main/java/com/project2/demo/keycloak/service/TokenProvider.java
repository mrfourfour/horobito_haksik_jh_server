package com.project2.demo.keycloak.service;

public interface TokenProvider {

    public Token issue(TokenRequest tokenRequest);

    public Token refresh(String refreshToken);

    public void signUp(TokenRequest tokenRequest);


}
