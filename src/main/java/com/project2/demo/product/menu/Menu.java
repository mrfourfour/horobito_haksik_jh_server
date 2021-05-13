package com.project2.demo.product.menu;


import com.project2.demo.product.category.Category;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Time startTime;

    @Embedded
    private Time FinishTime;

    private List<Category> categoryList;






}
