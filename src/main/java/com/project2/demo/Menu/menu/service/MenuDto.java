package com.project2.demo.Menu.menu.service;

import lombok.Value;

@Value
public class MenuDto {
    Long id;

    String title;

    String description;

    String imageURL;

    int price;
}
