package com.project2.demo.Menu.menu.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Value;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
public class MenuParameter {
    String title;

    int price;

    String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    LocalTime endTime;

    int amount;

    String imageURL;

}
