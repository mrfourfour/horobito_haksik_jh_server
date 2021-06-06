package com.project2.demo.order.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
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






}
