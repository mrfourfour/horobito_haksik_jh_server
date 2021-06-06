package com.project2.demo.order.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OrdererId ordererId;

    @Embedded
    private OrderTime orderTime;

    @Embedded
    private TotalPrice totalPrice;


    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST})
    private List<OrderLine> orderLines = new ArrayList<>();


    private Order(OrdererId ordererId) {
        this.ordererId = ordererId;
        this.orderTime = OrderTime.create(LocalDateTime.now());
        this.totalPrice = TotalPrice.create(0);
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
        this.totalPrice = TotalPrice.create(
                this.totalPrice.getTotalPrice()
                +orderLine.getTotalPrice().getTotalPrice()
        );
    }

    public static Order create(OrdererId ordererId){
        return new Order(ordererId);
    }

    public Long getOrdererId() {
        return ordererId.getId();
    }

    public LocalDateTime getOrderTime() {
        return orderTime.getOrderTime();
    }

    public int getTotalPrice(){
        return this.totalPrice.getTotalPrice();
    }
}
