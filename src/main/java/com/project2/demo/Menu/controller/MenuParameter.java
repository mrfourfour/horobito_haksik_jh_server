package com.project2.demo.Menu.controller;


import lombok.Value;

import java.time.LocalTime;
import java.time.MonthDay;

@Value
public class MenuParameter {
    String foodName;

    int price;

    LocalTime startTime;
    LocalTime endTime;

    MonthDay endDay;

    Boolean limited;

    String country;
    String food;


}
