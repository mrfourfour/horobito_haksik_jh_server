package com.project2.demo.Menu.service;


import com.project2.demo.Menu.controller.LimitDayParameter;
import com.project2.demo.Menu.controller.MenuParameter;
import com.project2.demo.Menu.domain.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MenuLimitAndUnLimitTest {

    @Mock
    MenuRepository menuRepository;

    MenuParameter menuParameter
            = new MenuParameter("pizza"
            , 50000,
            7,24, 8,30,
            0,0,false, "KOREA","NOODLE"
    );

    LimitDayParameter limitDayParameter
            = new LimitDayParameter(1, 1);


    @DisplayName("기간한정 설정 테스트1, 해당 메뉴 존재 안할 경우  ")
    @Test
    public void test1(){
        MenuService sut
                = new MenuService(menuRepository);

        when()


    }
}
