package com.project2.demo.category.categorizedFood.domain;


import lombok.NoArgsConstructor;

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
    private MenuId menuId;


    private CategorizedFood(CategoryId categoryId, MenuId menuId) {
        this.categoryId = categoryId;
        this.menuId = menuId;
    }

    public static CategorizedFood create(CategoryId categoryId, MenuId menuId){
        return new CategorizedFood(categoryId, menuId);
    }
}
