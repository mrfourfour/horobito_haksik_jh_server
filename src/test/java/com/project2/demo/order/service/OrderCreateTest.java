package com.project2.demo.order.service;

import com.project2.demo.Menu.menu.controller.MenuParameter;
import com.project2.demo.Menu.menu.service.MenuDto;
import com.project2.demo.Menu.menu.service.MenuService;
import com.project2.demo.order.controller.OrderLineParameter;
import com.project2.demo.order.controller.OrderParameter;
import com.project2.demo.order.domain.Order;
import com.project2.demo.order.domain.OrderRepository;
import com.project2.demo.order.domain.OrdererId;
import com.project2.demo.user.service.UserDto;
import com.project2.demo.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderCreateTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    UserService userService;

    @Mock
    MenuService menuService;

    MenuDto menuDto1 = new MenuDto(
            Long.parseLong("1"),
            "MenuOne",
            "첫 번째 메뉴",
            "url1",
            5000,
            LocalDateTime.now(),
            LocalDateTime.now()
    );

    MenuDto menuDto2 = new MenuDto(
            Long.parseLong("2"),
            "MenuTwo",
            "두 번째 메뉴",
            "url2",
            6000,
            LocalDateTime.now(),
            LocalDateTime.now()
    );





    @DisplayName("주문생성 테스트 1")
    @Test
    void createOrderTest1(){
        OrderService sut
                = new OrderService(orderRepository, userService, menuService);

        OrderLineParameter param1 = new OrderLineParameter(
                Long.parseLong("1"),
                1
        );
        OrderLineParameter param2 = new OrderLineParameter(
                Long.parseLong("2"),
                2
        );

        UserDto userDto = new UserDto(
                Long.parseLong("1"),
                "user 1"
        );

        List<OrderLineParameter> list = new ArrayList<>();
        list.add(param1);
        list.add(param2);
        OrderParameter orderParameter = new OrderParameter(list);

        when(userService.findLoggedUser()).thenReturn(userDto);

        when(menuService.getMenuInfo(any())).thenReturn(menuDto1);
        when(menuService.getMenuInfo(any())).thenReturn(menuDto1);

        when(menuService.getMenuInfo(any())).thenReturn(menuDto2);
        when(menuService.getMenuInfo(any())).thenReturn(menuDto2);




        sut.createOrder(orderParameter);

        verify(orderRepository, times(1)).save(any());

    }

}