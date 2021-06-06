package com.project2.demo.order.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
public class OrdererId {

    private Long id;

    private OrdererId(Long id) {
        this.id = id;
    }

    public static OrdererId create(Long id){
        return new OrdererId(id);
    }
}
