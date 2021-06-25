package com.project2.demo.category.category.service;

import com.project2.demo.category.controller.CategoryParameter;
import com.project2.demo.category.category.domain.*;
import com.project2.demo.Menu.menu.domain.MenuRepository;
import com.project2.demo.Menu.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final MenuService menuService;


    @Transactional
    public void create(CategoryParameter categoryParameter) {
        Category category = getCategory(categoryParameter);
        categoryRepository.save(category);
    }




    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        checkExistence(category);
        categoryRepository.delete(category);
    }

    public CategoryDto getCategoryInfo(Long categoryId) {
        Category category = getCategoryById(categoryId);
        checkExistence(category);
        return getCategoryDto(category);
    }

    private CategoryDto getCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getCategoryName(),
                category.getDescription());
    }


    private Category getCategory(CategoryParameter categoryParameter) {
        return Category.create(
                CategoryName.create(categoryParameter.getCategoryName()),
                Description.create(categoryParameter.getDescription())
        );
    }




    private void checkExistence(Category category) {
        if (category==null){
            throw new IllegalArgumentException();
        }
    }

    public void checkExistence(Long categoryId) {
        if (categoryRepository.findCategoryById(categoryId)==null){
            throw new IllegalArgumentException();
        }
    }


    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findCategoryById(categoryId);
    }
}
