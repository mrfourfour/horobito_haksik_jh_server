package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class SoldOutFlag {

    Boolean soldout;

    private SoldOutFlag(Boolean falseOrTrue) {
        this.soldout = falseOrTrue;
    }

    public static SoldOutFlag create(Boolean falseOrTrue){
        return new SoldOutFlag(falseOrTrue);
    }
}
