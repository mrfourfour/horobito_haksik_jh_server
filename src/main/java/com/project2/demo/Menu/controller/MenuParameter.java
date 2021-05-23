package com.project2.demo.Menu.controller;


import lombok.Value;

import java.time.LocalTime;
import java.time.MonthDay;

@Value
public class MenuParameter {
    String foodName;

    int price;

    int startHour;
    int startMinute;

    int endHour;
    int endMinute;

    int endMonth;
    int endDayOfMonth;

    Boolean limited;

    String country;
    String food;


}
