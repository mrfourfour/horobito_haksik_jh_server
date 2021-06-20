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

    @DisplayName("3. 비정상적인 덧셈 ")
    @Test
    void addMenu2(){
        MenuService sut
                = new MenuService(menuRepository);


        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forAdd = -3;
        Long id = Long.parseLong("1");

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);

        assertThrows(IllegalArgumentException.class, ()->{sut.addAmount(id, forAdd);});



    }

    @DisplayName("1. 정상적인 뺄셈 ")
    @Test
    void subtractMenu1(){
        MenuService sut
                = new MenuService(menuRepository);

        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forSubtract = 3;
        Long id = Long.parseLong("1");

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);

        sut.subtractAmount(id, forSubtract);

        assertEquals(originAmount-forSubtract, menu.getAmountOfFoodLeft().returnFoodLeft());




    }

    @DisplayName("2. 정상적인 뺄셈 (2). SoldOut 테스트  ")
    @Test
    void subtractMenu2(){

        MenuService sut
                = new MenuService(menuRepository);

        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forSubtract = menu.getAmountOfFoodLeft().returnFoodLeft();
        Long id = Long.parseLong("1");

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);
        System.out.println(menu.getSoldOut().getSoldOut());

        sut.subtractAmount(id, forSubtract);

        assertEquals(originAmount-forSubtract, menu.getAmountOfFoodLeft().returnFoodLeft());

        assertEquals(true, menu.getSoldOut().getSoldOut());
        System.out.println(menu.getSoldOut().getSoldOut());




    }

    @DisplayName("3. 비정상적인 뺄셈 (1), 음수 입력  ")
    @Test
    void subtractMenu3(){

        MenuService sut
                = new MenuService(menuRepository);

        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forSubtract = -1;
        Long id = Long.parseLong("1");

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);

        assertThrows(IllegalArgumentException.class, ()->sut.subtractAmount(id, forSubtract));


    }


    @DisplayName("4. 비정상적인 뺄셈 (1), 기존 값보다 더 많은 양 차감 시도   ")
    @Test
    void subtractMenu4(){

        MenuService sut
                = new MenuService(menuRepository);

        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forSubtract =  menu.getAmountOfFoodLeft().returnFoodLeft()+3;
        Long id = Long.parseLong("1");

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);

        assertThrows(IllegalArgumentException.class, ()->sut.subtractAmount(id, forSubtract));


    }

    @DisplayName("1. 정상적인 수정 ")
    @Test
    void ModifyMenu1(){

        MenuService sut
                = new MenuService(menuRepository);

        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forModify =  menu.getAmountOfFoodLeft().returnFoodLeft()+3;
        Long id = Long.parseLong("1");
        System.out.println( menu.getAmountOfFoodLeft().returnFoodLeft());

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);
        sut.modifyAmount(id, forModify);

        assertEquals(forModify, menu.getAmountOfFoodLeft().returnFoodLeft());
        System.out.println( menu.getAmountOfFoodLeft().returnFoodLeft());


    }

    @DisplayName("2. 비정상적인 수정 - 음수로 수정 시도 ")
    @Test
    void ModifyMenu2(){

        MenuService sut
                = new MenuService(menuRepository);

        int originAmount = menu.getAmountOfFoodLeft().returnFoodLeft();
        int forModify =  -1;
        Long id = Long.parseLong("1");
        System.out.println( menu.getAmountOfFoodLeft().returnFoodLeft());

        when(menuRepository.findMenuByIdAndDeleted(anyLong(), anyBoolean())).thenReturn(menu);

        assertThrows(IllegalArgumentException.class, ()->sut.modifyAmount(id, forModify));
        System.out.println( menu.getAmountOfFoodLeft().returnFoodLeft());


    }

}
