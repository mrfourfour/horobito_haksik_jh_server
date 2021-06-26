package com.project2.demo.category.categorizedFood.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class CategoryId {

    private Long categoryId;

    private CategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public static CategoryId create(Long categoryId){
        return new CategoryId(categoryId);
    }
}
