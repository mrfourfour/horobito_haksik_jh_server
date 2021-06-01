package com.project2.demo.Menu.category.service;

import com.project2.demo.Menu.category.controller.CategoryParameter;
import com.project2.demo.Menu.category.domain.*;
import com.project2.demo.Menu.menu.domain.MenuRepository;
import com.project2.demo.Menu.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;


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
    public void addFood(Long categoryId, Long menuId) {
        menuService.checkExistence(menuRepository.findMenuById(menuId));
        Category category = getCategoryById(categoryId);
        checkExistence(category);
        category.addMenu(FoodId.create(menuId));
    }

    @Transactional
    public void deleteMenuInCategory(Long categoryId, Long menuId) {
        Category category = getCategoryById(categoryId);
        checkExistence(category);
        FoodId foodId = FoodId.create(menuId);
        checkExistence(category, foodId);
        category.deleteFoodIdInCategory(foodId);
    }


    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        checkExistence(category);
        categoryRepository.delete(category);
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

    private void checkExistence(Category category, FoodId foodId) {
        category.findFoodId(foodId);
    }

    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findCategoryById(categoryId);
    }
}
