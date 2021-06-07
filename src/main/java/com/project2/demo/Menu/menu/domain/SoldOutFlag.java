package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class SoldOutFlag {

    Boolean soldOut;

    private SoldOutFlag(Boolean soldOut) {
        this.soldOut = soldOut;
    }

    public static SoldOutFlag create(Boolean falseOrTrue){
        return new SoldOutFlag(falseOrTrue);
    }
}
