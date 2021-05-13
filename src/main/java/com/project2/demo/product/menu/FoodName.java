package com.project2.demo.product.menu;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

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
