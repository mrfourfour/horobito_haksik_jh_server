package com.project2.demo.user.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Username {

    private String username;

    private Username(String username) {
        this.username = username;
    }


    public static Username create(String username) {
        return new Username(username);
    }
}
