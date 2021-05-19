package com.project2.demo.Menu.domain;


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


    private SoldOutFlag soldOut;

    private CategoryCountry country;

    private CategoryFood food;


    private Menu(FoodName foodName,
                 Price price,
                 Time salesTime,
                 CategoryCountry country,
                 CategoryFood food) {
        this.foodName = foodName;
        this.price = price;
        this.salesTime = salesTime;
        this.soldOut = SoldOutFlag.create(false);
        this.country = country;
        this.food = food;
    }

    public static Menu create(FoodName foodName,
                              Price price,
                              Time salesTime,
                              CategoryCountry country,
                              CategoryFood food) {
        return new Menu(foodName, price, salesTime, country, food);
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

    public void setSoldOut(){
        this.soldOut =  SoldOutFlag.create(true);
    }

    public void setUnSoldOut(){
        this.soldOut = SoldOutFlag.create(false);
    }


}




