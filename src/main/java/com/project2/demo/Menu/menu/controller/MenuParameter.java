package com.project2.demo.Menu.menu.controller;


import lombok.Getter;
import lombok.Value;

@Value
public class MenuParameter {
    String foodName;

    int price;

    String description;

    int startHour;
    int startMinute;

    int endHour;
    int endMinute;

    int endMonth;
    int endDayOfMonth;

    int limitedMonth;
    int limitedDayOfMonth;


    int amount;

    String imageURL;



}
