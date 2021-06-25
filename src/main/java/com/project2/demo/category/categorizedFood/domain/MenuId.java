package com.project2.demo.category.categorizedFood.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class MenuId {

    private Long foodId;

    private MenuId(Long foodId) {
        this.foodId = foodId;
    }

    public static MenuId create(Long foodId){
        return new MenuId(foodId);
    }
}
