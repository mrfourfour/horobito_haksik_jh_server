package com.project2.demo.order.service;

import lombok.Value;

@Value
public class OrderLineDto {
    Long orderLineId;
    Long menuId;
    String menuName;
    int price;
    int count;
    int totalPrice;
}
