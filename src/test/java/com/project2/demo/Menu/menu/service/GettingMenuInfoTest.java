package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.controller.MenuParameter;
import com.project2.demo.Menu.menu.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GettingMenuInfoTest {

    @Mock
    MenuRepository menuRepository;



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

    Menu menu = MenuHelper.create(
            Long.parseLong("1"),
            Title.create("testFood"),
            Price.create(10000),
            MenuDescription.create("for Test"),
            Time.create(LocalDateTime.parse("08:00:00", fmt), LocalDateTime.parse("20:00:00", fmt)),
            AmountOfFoodLeft.create(10),
            ImageURL.create("testURL")
    );


    @DisplayName("1. 메뉴 조회")
    @Test
    void getMenuInfoTest1(){
        MenuService sut
                = new MenuService(menuRepository);

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);

        System.out.println(sut.getMenuInfo(Long.parseLong("1")).toString());;


    }

    @DisplayName("2. 존재하지 않은 메뉴 조회")
    @Test
    void getMenuInfoTest2(){
        MenuService sut
                = new MenuService(menuRepository);


        ;

        assertThrows(IllegalArgumentException.class, ()->sut.getMenuInfo(Long.parseLong("1") ));


    }


}
