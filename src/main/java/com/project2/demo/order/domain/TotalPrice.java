package com.project2.demo.order.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class TotalPrice {

    private int totalPrice;

    private TotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static TotalPrice create(int totalPrice){
        return new TotalPrice(totalPrice);
    }
}
