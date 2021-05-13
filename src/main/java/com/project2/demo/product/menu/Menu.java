package com.project2.demo.product.menu;


import com.project2.demo.product.category.Category;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

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


    private Boolean soldOut;

    private List<Category> categoryList = new ArrayList<>();

    private Menu(FoodName foodName, Price price, Time salesTime) {
        this.foodName = foodName;
        this.price = price;
        this.salesTime = salesTime;
        this.soldOut = false;
    }

    public static Menu create(FoodName foodName, Price price, Time salesTime){
        return new Menu(foodName, price, salesTime);
    }

    public void LimitMenuSalePeriod(MonthDay period){
        this.salesTime = Time.limit(this.salesTime.startTime, this.salesTime.endTime, period);
    }
}
