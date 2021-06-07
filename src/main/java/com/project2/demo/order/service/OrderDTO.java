package com.project2.demo.order.service;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class OrderDTO {
    Long orderId;
    Long ordererId;
    LocalDateTime orderTime;
    List<OrderLineDto> orderLines;
    int totalPrice;

}
