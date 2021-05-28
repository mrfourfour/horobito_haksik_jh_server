package com.project2.demo.Menu.menu.controller;


import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class LimitDayParameter {

    int limitedMonth;
    int limitedDayOfMonth;

}
