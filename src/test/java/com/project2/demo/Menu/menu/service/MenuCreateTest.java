package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.controller.MenuParameter;
import com.project2.demo.Menu.menu.domain.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuCreateTest {

    @Mock
    MenuRepository menuRepository;


    @DisplayName("1. 메뉴 만들기")
    @Test
    void createMenu1(){

        MenuService sut
                = new MenuService(menuRepository);

        MenuParameter menuParameter
                = new MenuParameter("pizza"
                , 50000,
                "this is pizza",
                LocalTime.parse("08:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),
                LocalTime.parse("20:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),30,
                "pizza url"
        );

        sut.createMenu(menuParameter);
        verify(menuRepository, times(1)).save(any());

    }

    @DisplayName("2. 비정상적 메뉴 만들기 - 시간 순서 오류")
    @Test
    void createMenu2(){

        MenuService sut
                = new MenuService(menuRepository);

        MenuParameter menuParameter
                = new MenuParameter("pizza"
                , 50000,
                "this is pizza",
                LocalTime.parse("20:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),
                LocalTime.parse("08:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),30,
                "pizza url"
        );

        assertThrows(IllegalArgumentException.class, ()->sut.createMenu(menuParameter));



    }
}
