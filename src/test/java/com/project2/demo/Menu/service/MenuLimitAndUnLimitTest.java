package com.project2.demo.Menu.service;


import com.project2.demo.Menu.controller.LimitDayParameter;
import com.project2.demo.Menu.controller.MenuParameter;
import com.project2.demo.Menu.domain.Menu;
import com.project2.demo.Menu.domain.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


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
            7,24, 8,30,
            3,20,false, "KOREA","NOODLE"
    );
    LimitDayParameter limitDayParameter
            = new LimitDayParameter(1, 1);




    @DisplayName("기간한정 설정 테스트1, 해당 메뉴 존재 안할 경우  ")
    @Test
    public void test1(){
        MenuService sut
                = new MenuService(menuRepository);
        Long menuId = Long.parseLong("1");
        when(sut.getMenuById(menuId)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, ()->sut.limitMenu(menuId, limitDayParameter));

    }

    @DisplayName("기간한정 설정 테스트2, 이미 기간한정이 설정되어 있는 경우   ")
    @Test
    public void test2(){
        MenuService sut
                = new MenuService(menuRepository);
        Menu menu = sut.getMenu(sut.getTime(menuParameter), menuParameter);
        Long menuId = Long.parseLong("1");
        when(sut.getMenuById(menuId)).thenReturn(menu);
        assertThrows(IllegalArgumentException.class, ()->sut.limitMenu(menuId, limitDayParameter));
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
        sut.limitMenu(menuId, limitDayParameter);

        assertEquals(true, menu.discriminateLimit());
        System.out.println("설정 후 : " + menu.discriminateLimit());
    }
}
