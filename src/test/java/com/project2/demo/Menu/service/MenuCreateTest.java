package com.project2.demo.Menu.service;


import com.project2.demo.Menu.controller.MenuParameter;
import com.project2.demo.Menu.domain.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
                20,

                );

    }
}
