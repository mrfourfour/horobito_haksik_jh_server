package com.project2.demo.keycloak.infrastructure;


import com.project2.demo.keycloak.service.DuplicateUserSignUpException;
import com.project2.demo.keycloak.service.Token;
import com.project2.demo.keycloak.service.TokenProvider;
import com.project2.demo.keycloak.service.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KeyCloakTokenProvider implements TokenProvider {

    @Qualifier("keycloakWebClient")
    private final WebClient webClient;

    private final KeycloakSpringBootProperties properties;
    private final Keycloak keycloakAdminClient ;


    /*
    발급용 1
     */
    @Override
    public Token issue(TokenRequest tokenRequest){
        String username = tokenRequest.getUsername();
        String password = tokenRequest.getPassword();
        String resource = properties.getResource();
        String clientSecret = properties.getCredentials().toString();
        BodyInserters.FormInserter<String> formData = createFormData(resource, clientSecret, username, password);
        return fetchResouce(formData);

    }


    @Override
    public Token refresh(String refreshToken) {
        String resource = properties.getResource();
        String clientSecret =properties.getCredentials().toString();
        BodyInserters.FormInserter<String> formData
                = createFormData(resource, clientSecret, refreshToken);
        return null;
    }



    /*
    역할
    자원을 본격적으로 받아오는 것
    keyCloak도 RestApi를 지원하기때문에,
    이렇게 자원을 요청하는 것
     */
    private Token fetchResouce(BodyInserters.FormInserter<String> formData) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.pathSegment("token").build())
                .header(HttpHeaders.CONTENT_TYPE, mediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(formData)
                .retrieve()
                .onStatus()
                .onStatus()
                .bodyToMono(Token::new).block();
    }

    private BodyInserters.FormInserter<String> createFormData(
            String resource, String clientSecret, String refreshToken) {
        return BodyInserters.fromFormData("refresh_token", refreshToken)
                .with("client_id", resource)
                .with("client_secret", clientSecret)
                .with("grant_type", "refresh_token");
    }

    private BodyInserters.FormInserter<String> createFormData(String resource, String clientSecret, String username, String password) {
        return BodyInserters.fromFormData("client_id", resource)
                .with("grant_type", "password")
                .with("client_secret", clientSecret)
                .with("usernane", username)
                .with("password", password);
    }

    @Override
    public void signUp(TokenRequest tokenRequest) {
        UsersResource userResources = keycloakAdminClient
                .realm(properties.getRealm())
                .users();
        UserRepresentation userRepresentation
                = getUserRepresentation(tokenRequest);
        Response createUserResponse = userResources
                .create(userRepresentation);
        Response.StatusType statusInfo = createUserResponse.getStatusInfo();
        if (statusInfo.equals(Response.Status.CONFLICT)) {
            throw new DuplicateUserSignUpException();
        }
        if (statusInfo.equals(Response.Status.FORBIDDEN)){
            throw new IllegalArgumentException("keycloak configuration");
        }
    }

    private UserRepresentation getUserRepresentation(TokenRequest tokenRequest) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(tokenRequest.getPassword());
        credential.setTemporary(false);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(tokenRequest.getEmail());
        userRepresentation.setUsername(tokenRequest.getUsername());
        userRepresentation.setCredentials(Collections.singletonList(credential));
        userRepresentation.setRealmRoles(Collections.singletonList("USER_ROLE"));

        return userRepresentation;

    }


}

@Configuration
class keyCloakWebClientConfig{

    /*
    회원가입에 사용되는 녀석, AdminClietn
     */
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

    /*
    keycloak 에 연결을 하고자 하는 곳으로 연결이 되어 있다.
     */
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
