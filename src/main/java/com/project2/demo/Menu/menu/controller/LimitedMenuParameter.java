package com.project2.demo.Menu.menu.controller;


import lombok.Value;

@Value
public class LimitedMenuParameter {

    String foodName;

    int price;

    int startHour;
    int startMinute;

    int endHour;
    int endMinute;

    int endMonth;
    int endDayOfMonth;

    int limitedMonth;
    int limitedDayOfMonth;

    boolean limited;

    int amount;


}
