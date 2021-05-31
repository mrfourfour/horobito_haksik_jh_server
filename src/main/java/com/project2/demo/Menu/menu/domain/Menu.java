package com.project2.demo.Menu.menu.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.MonthDay;


@Entity
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    private FoodName foodName;

    @Embedded
    private Price price;

    @Embedded
    private Time salesTime;

    @Embedded
    private AmountOfFoodLeft amountOfFoodLeft;

    private SoldOutFlag soldOut;

    private boolean deleted;


    private Menu(FoodName foodName,
                 Price price,
                 Time salesTime,
                 AmountOfFoodLeft amount) {
        this.foodName = foodName;
        this.price = price;
        this.salesTime = salesTime;
        this.soldOut = SoldOutFlag.create(false);
        this.deleted = false;
        this.amountOfFoodLeft = amount;
    }


    public static Menu create(FoodName foodName,
                              Price price,
                              Time salesTime,
                              AmountOfFoodLeft amount) {
        return new Menu(foodName, price, salesTime, amount);
    }

    public void increaseAmountOfFoodLeft(int amountForAdd){
        this.amountOfFoodLeft = AmountOfFoodLeft.create(this.amountOfFoodLeft.returnFoodLeft() + amountForAdd);
    }

    public void decreaseAmountOfFoodLeft(int amountForAdd){
        if (amountForAdd>this.amountOfFoodLeft.returnFoodLeft()){
            throw new IllegalArgumentException();
        }
        this.amountOfFoodLeft = AmountOfFoodLeft.create(this.amountOfFoodLeft.returnFoodLeft() - amountForAdd);
    }

    public void limit(MonthDay limitedDay) {
        this.salesTime
                = Time.limit(
                this.salesTime.startTime,
                this.salesTime.endTime,
                limitedDay
        );
    }

    ;

    public void unLimit() {
        this.salesTime = Time.create(this.salesTime.startTime,
                this.salesTime.endTime);
    }

    public void setSoldOut() {
        this.soldOut = SoldOutFlag.create(true);
    }

    public void setUnSoldOut() {
        this.soldOut = SoldOutFlag.create(false);
    }

    public Boolean discriminateSoldOut() {
        return this.soldOut.getSoldOut();
    }

    public Boolean discriminateLimit() {
        return this.salesTime.limited;
    }

    public void delete() {
        this.deleted = true;
    }


}




