package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Title {

    private String title;

    private Title(String title) {
        this.title = title;
    }

    public static Title create(String title){
        return new Title(title);
    }


}
