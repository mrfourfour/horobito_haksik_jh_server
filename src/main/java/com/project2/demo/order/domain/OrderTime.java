package com.project2.demo.order.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
public class OrderTime {

    private LocalDateTime orderTime;

    private OrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public static OrderTime create(LocalDateTime orderTime){
        return new OrderTime(orderTime);
    }
}
