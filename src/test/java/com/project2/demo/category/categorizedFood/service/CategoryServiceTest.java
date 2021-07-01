//package com.project2.demo.category.categorizedFood.service;
//
//
//import com.project2.demo.Menu.menu.domain.*;
//import com.project2.demo.Menu.menu.service.MenuService;
//import com.project2.demo.category.categorizedFood.domain.CategorizedFoodRepository;
//import com.project2.demo.category.category.domain.Category;
//import com.project2.demo.category.category.domain.CategoryName;
//import com.project2.demo.category.category.domain.CategoryRepository;
//import com.project2.demo.category.category.domain.Description;
//import com.project2.demo.category.category.service.CategoryHelper;
//import com.project2.demo.category.category.service.CategoryService;
//import com.project2.demo.category.controller.CategoryParameter;
//import com.project2.demo.util.RandomParametersExtension;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
//import java.time.temporal.ChronoField;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyBoolean;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@ExtendWith(RandomParametersExtension.class)
//class CategoryServiceTest {
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    CategorizedFoodRepository categorizedFoodRepository;
//
//    @Autowired
//    MenuRepository menuRepository;
//
//
//
//
//
//    @DisplayName("create categorizedFood test 1. normal condition")
//    @Test
//    public void test1(){
//        MenuService menuService = new MenuService(
//                menuRepository
//        );
//
//        CategoryService categoryService = new CategoryService(
//                categoryRepository,
//                menuRepository,
//                categorizedFoodRepository
//        );
//        CategorizedFoodService sut = new CategorizedFoodService(
//                menuService,
//                menuRepository,
//                categoryService,
//                categorizedFoodRepository
//        );
//
//        sut.addFoodInCategory(1L, 2L);
//
//
//    }
//
//}