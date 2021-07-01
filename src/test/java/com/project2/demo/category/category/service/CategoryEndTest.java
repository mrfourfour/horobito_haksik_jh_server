package com.project2.demo.category.category.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CategoryEndTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void hello() {
        WebTestClient.ResponseSpec response = request(1L, 2);
        response
                .expectStatus().isOk()
                .expectBodyList(CategoryDetailDto.class)
                .hasSize(2);
    }

    private WebTestClient.ResponseSpec request(Long cursor, int size) {
        return webTestClient
                .get()
                .uri("/categories/details?cursor={cursor}&size={size}", cursor, size)
                .exchange();
    }

}
