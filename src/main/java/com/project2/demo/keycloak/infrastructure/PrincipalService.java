package com.project2.demo.keycloak.infrastructure;


import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService {

    public KeycloakPrincipal<?> getUserPrincipal(){
        Object principal
                = SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        if (principal instanceof  KeycloakPrincipal){
            ((KeycloakPrincipal<?>) principal)
                    .getKeycloakSecurityContext().getAuthorizationContext()
                    .getPermissions();
            return (KeycloakPrincipal<?>) principal;
        }
        throw new IllegalStateException("Not Authenticated From KeyCloak");
    };

}
