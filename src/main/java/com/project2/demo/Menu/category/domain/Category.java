package com.project2.demo.Menu.category.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    private CategoryName categoryName;

    @Embedded
    private Description description;

    @ElementCollection
    @CollectionTable(
            name = "foodo_id",
            joinColumns = @JoinColumn(name = "category_id")
    )
    private Set<FoodId> foodIds;


    private Category(CategoryName categoryName, Description description) {
        this.categoryName = categoryName;
        this.description = description;
        this.foodIds = new HashSet<>();
    }

    public static Category create(CategoryName categoryName,
                                  Description description){
        return new Category(categoryName, description);
    }

    public void addMenu(FoodId foodId) {

        this.foodIds.add(foodId);
    }

    public void findFoodId(FoodId foodId) {
        if (!this.foodIds.contains(foodId)){
            throw new IllegalArgumentException();
        }
    }

    public void deleteFoodIdInCategory(FoodId foodId) {
        this.foodIds.remove(foodId);
    }
}
