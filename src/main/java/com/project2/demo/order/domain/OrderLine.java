package com.project2.demo.order.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("order")
    private Order order;

    @Embedded
    private OrderedMenuInfo orderedMenuInfo;

    @Embedded
    private Count count;

    @Embedded
    private TotalPrice totalPrice;


    private OrderLine(
            Order order,
            OrderedMenuInfo orderedMenuInfo,
            Count count,
            TotalPrice totalPrice) {
        this.order = order;
        this.orderedMenuInfo = orderedMenuInfo;
        this.count = count;
        this.totalPrice = getTotalPrice(count, orderedMenuInfo);
    }

    private TotalPrice getTotalPrice(Count count, OrderedMenuInfo orderedMenuInfo) {
        return TotalPrice.create(count.getCount() * orderedMenuInfo.getPrice());
    }
}
