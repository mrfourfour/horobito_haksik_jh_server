package com.project2.demo.order.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OrdererId ordererId;

    @Embedded
    private OrderTime orderTime;


    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST})
    private List<OrderLine> orderLines = new ArrayList<>();


    private Order(OrdererId ordererId) {
        this.ordererId = ordererId;
        this.orderTime = OrderTime.create(LocalDateTime.now());
    }

    private void addOrderLine(OrderLine orderLine){
        this.orderLines.add(orderLine);
    }

    public static Order create(OrdererId ordererId){
        return new Order(ordererId);
    }
}
