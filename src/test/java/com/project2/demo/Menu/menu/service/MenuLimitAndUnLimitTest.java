package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.controller.MenuParameter;
import com.project2.demo.Menu.menu.domain.Menu;
import com.project2.demo.Menu.menu.domain.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuLimitAndUnLimitTest {

    @Mock
    MenuRepository menuRepository;

    MenuParameter menuParameter
            = new MenuParameter("pizza"
            , 50000,
            "this is pizza",
            LocalTime.parse("08:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),
            LocalTime.parse("20:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),30,
            "pizza url"
    );



    @DisplayName("기간한정 설정 테스트1, 해당 메뉴 존재 안할 경우  ")
    @Test
    public void test1(){
        MenuService sut
                = new MenuService(menuRepository);
        Long menuId = Long.parseLong("1");
        when(sut.getMenuById(menuId)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, ()->sut.limitMenu(menuId));

    }

    @DisplayName("기간한정 설정 테스트2, 이미 기간한정이 설정되어 있는 경우   ")
    @Test
    public void test2(){
        MenuService sut
                = new MenuService(menuRepository);
        Menu menu = sut.getMenu(sut.getTime(menuParameter), menuParameter);
        menu.limit();
        Long menuId = Long.parseLong("1");
        when(sut.getMenuById(menuId)).thenReturn(menu);
        assertThrows(IllegalArgumentException.class, ()->sut.limitMenu(menuId));
    }

    @DisplayName("기간한정 설정 테스트3, 기간 한정 설정 ")
    @Test
    public void test3(){
        MenuService sut
                = new MenuService(menuRepository);
        Menu menu = sut.getMenu(sut.getTime(menuParameter), menuParameter);
        Long menuId = Long.parseLong("1");
        when(sut.getMenuById(menuId)).thenReturn(menu);
        System.out.println("설정 전 : " + menu.discriminateLimit());
        sut.limitMenu(menuId);

        assertEquals(true, menu.discriminateLimit());
        System.out.println("설정 후 : " + menu.discriminateLimit());
    }
}
