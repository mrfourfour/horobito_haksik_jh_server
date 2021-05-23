package com.project2.demo.Menu.controller;


import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class LimitDayParameter {

    int limitedMonth;
    int limitedDayOfMonth;

}
