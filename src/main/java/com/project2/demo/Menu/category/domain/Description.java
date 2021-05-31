package com.project2.demo.Menu.category.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
public class Description {

    private String description;

    private Description(String description) {
        this.description = description;
    }

    public static Description create(String description){
        return new Description(description);
    }
}
