package com.project2.demo.category.category.domain;


import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    private CategoryName categoryName;

    @Embedded
    private Description description;

//    @ElementCollection
//    @CollectionTable(
//            name = "foodo_id",
//            joinColumns = @JoinColumn(name = "category_id")
//    )
//    private Set<FoodId> foodIds;


    private Category(CategoryName categoryName, Description description) {
        this.categoryName = categoryName;
        this.description = description;
//        this.foodIds = new HashSet<>();
    }

    public static Category create(CategoryName categoryName,
                                  Description description){
        return new Category(categoryName, description);
    }

//    public void addMenu(FoodId foodId) {
//
//        this.foodIds.add(foodId);
//    }

//    public void findFoodId(FoodId foodId) {
//        if (!this.foodIds.contains(foodId)){
//            throw new IllegalArgumentException();
//        }
//    }

//    public void deleteFoodIdInCategory(FoodId foodId) {
//        this.foodIds.remove(foodId);
//    }

    public Long getId() {
        return this.id;
    }

    public String getCategoryName() {
        return this.categoryName.getCategoryName();
    }

    public String  getDescription() {
        return this.description.getDescription();
    }

    public void changeTitleAndDescription(String title, String description) {
        this.categoryName = CategoryName.create(title);
        this.description = Description.create(description);
    }
}
