package com.project2.demo.keycloak.infrastructure;


import com.project2.demo.keycloak.service.Token;
import com.project2.demo.keycloak.service.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KeyCloakTokenProvider {

    private final WebClient webClient;
    private final KeycloakSpringBootProperties properties;
    private final KeycloakAdminClient keycloak;



}

@Configuration
class keyCloakWebClientConfig{

    @Bean
    public Keycloak keyCloakAdminClient(KeycloakSpringBootProperties properties) {
        return KeycloakBuilder.builder()
                .serverUrl(properties.getAuthServerUrl())
                .realm(properties.getRealm())
                .clientId(properties.getResource())
                .clientSecret(properties.getCredentials().toString())
                .grantType("client_credentials")
                .build();
    }

    @Bean
    public WebClient keycloakWebClient(KeycloakSpringBootProperties properties){

        String baseUrl = getBaseUrl(properties);
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    private String getBaseUrl(KeycloakSpringBootProperties properties) {
        return "@{properties.authServiceUrl}/realms/" +
                "${properties.realm}/protocol/openid-connect";
    }
}
