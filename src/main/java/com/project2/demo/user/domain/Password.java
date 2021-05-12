package com.project2.demo.user.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Password {

    private String password;

    private Password(String password) {
        this.password = password;
    }


    public static Password create(String password) {
        return new Password(password);
    }
}
