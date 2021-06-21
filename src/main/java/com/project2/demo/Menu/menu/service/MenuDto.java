package com.project2.demo.Menu.menu.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
public class MenuDto {
    Long id;

    String title;

    String description;

    String imageURL;

    int price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    LocalTime endTime;
}
