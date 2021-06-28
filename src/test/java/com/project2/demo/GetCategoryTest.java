package com.project2.demo;


import com.project2.demo.Menu.menu.domain.*;
import com.project2.demo.Menu.menu.service.MenuService;
import com.project2.demo.category.categorizedFood.domain.CategorizedFoodRepository;
import com.project2.demo.category.category.domain.Category;
import com.project2.demo.category.category.domain.CategoryName;
import com.project2.demo.category.category.domain.CategoryRepository;
import com.project2.demo.category.category.domain.Description;
import com.project2.demo.category.category.service.CategoryHelper;
import com.project2.demo.category.category.service.CategoryService;
import com.project2.demo.category.controller.CategoryParameter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCategoryTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    MenuRepository menuRepository;

    @Mock
    CategorizedFoodRepository categorizedFoodRepository;

    Category categoryForTest
            = CategoryHelper.create(
            Long.parseLong("1"),
            CategoryName.create("test"),
            Description.create("for Test"));

    @DisplayName("getTest 1. Normal condition")
    @Test
    public void test(){
        CategoryService sut = new CategoryService(
                categoryRepository,
                menuRepository,
                categorizedFoodRepository);

        Long id = Long.parseLong("1");
        when(categoryRepository.findCategoryById(anyLong())).thenReturn(categoryForTest);
        System.out.println(sut.get(id).toString());
    }


}
