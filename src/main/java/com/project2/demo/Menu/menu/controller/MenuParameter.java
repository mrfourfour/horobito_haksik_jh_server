package com.project2.demo.Menu.menu.controller;


import lombok.Getter;
import lombok.Value;

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

    boolean limited;

    int amount;



}
