package com.project2.demo.keycloak.infrastructure;


import com.project2.demo.keycloak.service.Token;
import com.project2.demo.keycloak.service.TokenProvider;
import com.project2.demo.keycloak.service.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpHeaders;

@Component
@RequiredArgsConstructor
public class KeyCloakTokenProvider implements TokenProvider {

    @Qualifier("keycloakWebClient")
    private final WebClient webClient;

    private final KeycloakSpringBootProperties properties;
    private final KeycloakAdminClient keycloak;




    @Override
    public Token issue(TokenRequest tokenRequest){
        String username = tokenRequest.getUsername();
        String password = tokenRequest.getPassword();
        String resource = properties.getResource();
        String clientSecret = properties.getCredentials().toString();
        BodyInserters.FormInserter<String> formData = createFormData(resource, clientSecret, username, password);
        return fetchResouce(formData);

    }

    private Token fetchResouce(BodyInserters.FormInserter<String> formData) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.pathSegment("token").build())
                .header(HttpHeaders.CONTENT_TYPE, mediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(formData)
                .retrieve()
                .onStatus(stataus -> stataus.is4xxClientError())
                .onStatus()
                .bodyToMono(Token::new).block();
    }

    private BodyInserters.FormInserter<String> createFormData(String resource, String clientSecret, String username, String password) {
        return BodyInserters.fromFormData("client_id", resource)
                .with("grant_type", "password")
                .with("client_secret", clientSecret)
                .with("usernane", username)
                .with("password", password);
    }

    @Override
    public Token refresh(String refreshToken) {
        return null;
    }

    @Override
    public void signUp(TokenRequest tokenRequest) {

    }


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
