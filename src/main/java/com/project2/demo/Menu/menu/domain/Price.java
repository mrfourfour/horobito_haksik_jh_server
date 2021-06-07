package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Price {

    private int prioce ;

    private Price(int prioce) {
        this.prioce = prioce;
    }

    public static Price create(int price){
        return new Price(price);
    }
}
