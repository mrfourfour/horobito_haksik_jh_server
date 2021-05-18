package com.project2.demo.Menu.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
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
