package com.project2.demo.order.controller;


import lombok.Value;

import java.util.List;

@Value
public class OrderParameter {
    List<OrderLineParameter> orderLineList;
}
