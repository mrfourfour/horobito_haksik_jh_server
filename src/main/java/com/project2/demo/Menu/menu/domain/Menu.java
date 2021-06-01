package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.MonthDay;


@Entity
@Getter
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
    private MenuDescription menuDescription;

    @Embedded
    private Time salesTime;

    @Embedded
    private AmountOfFoodLeft amountOfFoodLeft;

    @Embedded
    private ImageURL imageURL;

    @Embedded
    private SoldOutFlag soldOut;

    private boolean deleted;


    private Menu(FoodName foodName,
                 Price price,
                 MenuDescription menuDescription,
                 Time salesTime,
                 AmountOfFoodLeft amount,
                 ImageURL imageURL
                ) {
        this.foodName = foodName;
        this.price = price;
        this.menuDescription = menuDescription;
        this.salesTime = salesTime;
        this.soldOut = SoldOutFlag.create(false);
        this.deleted = false;
        this.amountOfFoodLeft = amount;
        this.imageURL = imageURL;
    }


    public static Menu create(FoodName foodName,
                              Price price,
                              MenuDescription menuDescription,
                              Time salesTime,
                              AmountOfFoodLeft amount,
                              ImageURL imageURL
                              ) {
        return new Menu(foodName, price, menuDescription, salesTime, amount, imageURL);
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


    public void checkSoldOut() {
        if (this.amountOfFoodLeft.soldOut()){
            this.soldOut = SoldOutFlag.create(true);
        }
    }


    public void changeAmount(int amount) {
        if (amount<0){
            throw  new IllegalArgumentException();
        }
        this.amountOfFoodLeft = AmountOfFoodLeft.create(amount);
    }

    public Long getId() {
        return this.id;
    }

    public String getFoodName() {
        return this.foodName.getFoodName();
    }

    public int getPrice() {
        return this.price.getPrioce();
    }

    public String  getMenuDescription() {
        return menuDescription.getMenuDescription();
    }

    public String  getImageURL() {
        return imageURL.getImageURL();
    }
}




