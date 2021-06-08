package com.project2.demo.keycloak.infrastructure;


import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;

@Component
public class KeyCloakTokenProvider {

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
        return WebClient.builder().baseUrl(baseUrl).buld();
    }

    private String getBaseUrl(KeycloakSpringBootProperties properties) {
        return "@{properties.authServiceUrl}/realms/" +
                "${properties.realm}/protocol/openid-connect";
    }
}
