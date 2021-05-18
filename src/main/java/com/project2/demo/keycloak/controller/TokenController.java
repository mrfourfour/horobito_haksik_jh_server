package com.project2.demo.keycloak.controller;


import com.project2.demo.keycloak.infrastructure.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final PrincipalService principalService;


    @GetMapping()
    public KeycloakPrincipal<?> getMe(){

        return principalService.getUserPrincipal();


    }
}
