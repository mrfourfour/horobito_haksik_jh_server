package com.project2.demo.Menu.controller;


import lombok.Getter;
import lombok.Value;

import java.time.LocalTime;
import java.time.MonthDay;

@Value
@Getter
public class MenuParameter {
    String foodName;

    int price;

    int startHour;
    int startMinute;

    int endHour;
    int endMinute;

    int endMonth;
    int endDayOfMonth;

    boolean limited;

    String country;
    String food;


}
