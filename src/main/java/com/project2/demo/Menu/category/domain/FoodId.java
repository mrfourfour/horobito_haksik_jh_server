package com.project2.demo.Menu.category.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class FoodId {

    private Long foodId;

    private FoodId(Long foodId) {
        this.foodId = foodId;
    }

    public static FoodId create(Long foodId){
        return new FoodId(foodId);
    }
}
