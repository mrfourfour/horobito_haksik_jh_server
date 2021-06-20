package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.domain.*;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddAndSubtractAndModifyAmountTest {

    @Mock
    MenuRepository menuRepository;

    DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .parseDefaulting(ChronoField.EPOCH_DAY, 0)
            .toFormatter();

    Menu menu = MenuHelper.create(
            Long.parseLong("1"),
            Title.create("testFood"),
            Price.create(10000),
            MenuDescription.create("for Test"),
            Time.create(LocalTime.parse("08:00:00", fmt), LocalTime.parse("20:00:00", fmt)),
            AmountOfFoodLeft.create(10),
            ImageURL.create("testURL")
    );

    @DisplayName("1. 정상적인 덧셈 ")
    @Test
    void addMenu1(){

        MenuService sut
                = new MenuService(menuRepository);


        Long id = Long.parseLong("1");

        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forAdd = 3;

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);

        sut.addAmount(id, forAdd);

        assertEquals(originAmount+forAdd, menu.getAmountOfFoodLeft().returnFoodLeft());


    }

    @DisplayName("2. 정상적인 덧셈 - SoldOut 상태 변경 확인 ")
    @Test
    void addMenu(){

        MenuService sut
                = new MenuService(menuRepository);


        Long id = Long.parseLong("1");

        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forAdd = 3;

        int forSubtract = menu.getAmountOfFoodLeft().returnFoodLeft();
        System.out.println("처음 상태 : " + menu.getSoldOut().getSoldOut());

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);

        sut.subtractAmount(id, forSubtract);
        System.out.println("차감한 후의 상태 : " + menu.getSoldOut().getSoldOut());
        sut.addAmount(id, forAdd);

        assertEquals(forAdd, menu.getAmountOfFoodLeft().returnFoodLeft());
        System.out.println("덧셈 한 후의 상태 : " + menu.getSoldOut().getSoldOut());



    }
}
