package com.project2.demo.Menu.menu.service;

import com.project2.demo.Menu.menu.domain.*;

public class MenuHelper {
    private MenuHelper(){};

    public static Menu create(
            Long id,
            Title title,
            Price price,
            MenuDescription menuDescription,
            Time time,
            AmountOfFoodLeft amount,
            ImageURL imageURL){
        Menu menu = Menu.create(
                title,
                price,
                menuDescription,
                time,
                amount,
                imageURL
        );
        menu.setId(id);
        return menu;
    };
}