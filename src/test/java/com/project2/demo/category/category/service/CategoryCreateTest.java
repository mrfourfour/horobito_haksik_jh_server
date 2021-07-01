package com.project2.demo.category.category.service;

import com.project2.demo.Menu.menu.domain.*;
import com.project2.demo.Menu.menu.service.MenuService;
import com.project2.demo.category.categorizedFood.domain.CategorizedFoodRepository;
import com.project2.demo.category.category.domain.CategoryRepository;
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
class CategoryCreateTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    MenuRepository menuRepository;

    @Mock
    CategorizedFoodRepository categorizedFoodRepository;

    CategoryParameter categoryParam
            = new CategoryParameter(
                    "testCategory",
            "this is for tes"
    );


    @DisplayName("카테고리 생성 1. 정상적인 생성")
    @Test
    public void test1(){
        CategoryService sut = new CategoryService( // ???????
                categoryRepository,
                menuRepository,
                categorizedFoodRepository);


        sut.create(categoryParam);
        verify(categoryRepository, times(1)).save(any());

    }

}