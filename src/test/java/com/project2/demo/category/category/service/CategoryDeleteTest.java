package com.project2.demo.category.category.service;


import com.project2.demo.Menu.menu.domain.*;
import com.project2.demo.Menu.menu.service.MenuService;
import com.project2.demo.category.categorizedFood.domain.CategorizedFoodRepository;
import com.project2.demo.category.category.domain.Category;
import com.project2.demo.category.category.domain.CategoryName;
import com.project2.demo.category.category.domain.CategoryRepository;
import com.project2.demo.category.category.domain.Description;
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
public class CategoryDeleteTest {

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

    @DisplayName("카테고리 삭제 1. 정상적인 삭제의 경우")
    @Test
    public void test1(){
        CategoryService sut = new CategoryService( // ???????
                categoryRepository,
                menuRepository,
                categorizedFoodRepository);

        when(categoryRepository.findCategoryById(anyLong()))
                .thenReturn(categoryForTest);

        sut.deleteCategory(anyLong());
        verify(categoryRepository, times(1))
                .delete(any());


    }

}
