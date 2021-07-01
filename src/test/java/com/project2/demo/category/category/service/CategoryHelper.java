package com.project2.demo.category.category.service;

import com.project2.demo.category.category.domain.Category;
import com.project2.demo.category.category.domain.CategoryName;
import com.project2.demo.category.category.domain.Description;

public class CategoryHelper {
    private CategoryHelper(){};

    public static Category create(
            Long id,
            CategoryName categoryName,
            Description description
    ){
        Category category = Category.create(
                categoryName, description
        );

        category.setId(id);
        return category;
    }
}
