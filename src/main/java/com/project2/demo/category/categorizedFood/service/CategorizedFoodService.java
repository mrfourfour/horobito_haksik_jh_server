package com.project2.demo.category.categorizedFood.service;


import com.project2.demo.Menu.menu.domain.MenuRepository;
import com.project2.demo.Menu.menu.service.MenuService;
import com.project2.demo.category.categorizedFood.domain.CategorizedFood;
import com.project2.demo.category.categorizedFood.domain.CategorizedFoodRepository;
import com.project2.demo.category.categorizedFood.domain.CategoryId;
import com.project2.demo.category.categorizedFood.domain.MenuId;
import com.project2.demo.category.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CategorizedFoodService {

    private final MenuService menuService;
    private final MenuRepository menuRepository;
    private final CategoryService categoryService;
    private final CategorizedFoodRepository categorizedFoodRepository;

    @Transactional
    public void addFoodInCategory(Long categoryId, Long menuId) {
        checkAlreadyExistence(categoryId, menuId);
        menuService.checkExistence(menuId);
        categoryService.checkExistence(categoryId);
        CategorizedFood categorizedFood = CategorizedFood.create(
                CategoryId.create(categoryId), MenuId.create(menuId)
        );
        categorizedFoodRepository.save(categorizedFood);
    }

    private void checkAlreadyExistence(Long categoryId, Long menuId) {
        if (categorizedFoodRepository.findByCategoryIdAndMenuId(CategoryId.create(categoryId), MenuId.create(menuId))!=null){
            throw new IllegalArgumentException();
        }
    }

//    @Transactional
//    public void deleteMenuInCategory(Long categoryId, Long menuId) {
//        Category category = categoryService.getCategoryById(categoryId);
//        checkExistence(category);
//        FoodId foodId = FoodId.create(menuId);
//        checkExistence(category, foodId);
//        category.deleteFoodIdInCategory(foodId);
//    }
//
//
//    private void checkExistence(Category category, FoodId foodId) {
//        category.findFoodId(foodId);
//    }
}
