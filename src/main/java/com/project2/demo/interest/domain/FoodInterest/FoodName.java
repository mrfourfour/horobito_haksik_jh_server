package com.project2.demo.interest.domain.FoodInterest;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class FoodName {

    private String foodname;

    private FoodName(String foodname){
        this.foodname= foodname;
    }

    public static FoodName create(String foodname){
        return new FoodName(foodname);
    }
}
