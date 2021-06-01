package com.project2.demo.Menu.category.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class CategoryName {

    private String categoryName;



    private CategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static CategoryName create(String categoryName){
        return new CategoryName(categoryName);
    }
}
