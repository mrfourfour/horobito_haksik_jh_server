package com.project2.demo.order.controller;


import lombok.Value;

@Value
public class OrderLineParameter {

    Long menuId;

    int count;
}
