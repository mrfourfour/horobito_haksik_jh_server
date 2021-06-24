package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Price {

    private int price ;

    private Price(int price) {
        this.price = price;
    }

    public static Price create(int price){
        return new Price(price);
    }
}
