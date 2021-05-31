package com.project2.demo.Menu.category.service;

import com.project2.demo.Menu.category.controller.CategoryParameter;
import com.project2.demo.Menu.category.domain.Category;
import com.project2.demo.Menu.category.domain.CategoryName;
import com.project2.demo.Menu.category.domain.CategoryRepository;
import com.project2.demo.Menu.category.domain.Description;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Transactional
    public void create(CategoryParameter categoryParameter) {
        Category category = getCategory(categoryParameter);
        categoryRepository.save(category);
    }

    private Category getCategory(CategoryParameter categoryParameter) {
        return Category.create(
                CategoryName.create(categoryParameter.getCategoryName()),
                Description.create(categoryParameter.getDescription())
        );
    }


    @
    public void addFood() {
    }
}
