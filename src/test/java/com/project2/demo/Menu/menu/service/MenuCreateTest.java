package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.controller.MenuParameter;
import com.project2.demo.Menu.menu.domain.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

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
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .parseDefaulting(ChronoField.EPOCH_DAY, 0)
                .toFormatter();

        MenuParameter menuParameter
                = new MenuParameter("pizza"
                , 50000,
                "this is pizza",
                LocalDateTime.parse("08:00:00", fmt), LocalDateTime.parse("20:00:00", fmt),30,
                "pizza url"
                );

        sut.createMenu(menuParameter);
        verify(menuRepository, times(1)).save(any());

    }
}
