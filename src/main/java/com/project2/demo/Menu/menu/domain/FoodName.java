package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class FoodName {

    private String foodName;

    private FoodName(String foodName) {
        this.foodName = foodName;
    }

    public static FoodName create(String foodName){
        return new FoodName(foodName);
    }


}
