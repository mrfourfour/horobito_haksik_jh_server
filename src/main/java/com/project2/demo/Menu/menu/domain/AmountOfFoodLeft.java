package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class AmountOfFoodLeft {

    private int amountOfFoodLeft;

    private AmountOfFoodLeft(int amountOfFoodLeft) {
        this.amountOfFoodLeft = amountOfFoodLeft;
    }

    public static AmountOfFoodLeft create(int amount){
        return new AmountOfFoodLeft(amount);
    }

    public int returnFoodLeft(){
        return this.amountOfFoodLeft;
    }

}
