package com.project2.demo.category.categorizedFood.domain;


import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class CategorizedFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CategoryId categoryId;

    @Embedded
    private FoodId foodId;


    private CategorizedFood(CategoryId categoryId, FoodId foodId) {
        this.categoryId = categoryId;
        this.foodId = foodId;
    }

    public static CategorizedFood create(CategoryId categoryId, FoodId foodId){
        return new CategorizedFood(categoryId, foodId);
    }
}
