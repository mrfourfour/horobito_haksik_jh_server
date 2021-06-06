package com.project2.demo.order.service;


import com.project2.demo.Menu.menu.service.MenuService;
import com.project2.demo.order.controller.OrderLineParameter;
import com.project2.demo.order.controller.OrderParameter;
import com.project2.demo.order.domain.*;
import com.project2.demo.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Getter
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final MenuService menuService;


    @Transactional
    public OrderDTO createOrder(OrderParameter orderParameter){
        OrdererId ordererId
                = OrdererId.create(userService.findLoggedUser().getUserId());
        Order order = Order.create(ordererId);
        addOrderLines(order, orderParameter);
        orderRepository.save(order);

        return getOrderDto(order);
    }

    private OrderDTO getOrderDto(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getOrdererId(),
                order.getOrderTime(),
                order.getOrderLines(),
                order.getTotalPrice()
        )
    }

    private void addOrderLines(Order order, OrderParameter orderParameter) {
        int size = orderParameter.getOrderLineList().size();
        for(int i=0; i<size; i++){
            order.addOrderLine(getOrderLine(orderParameter, i));
        }

    }

    private OrderLine getOrderLine(OrderParameter orderParameter, int index) {
        OrderLineParameter list = orderParameter.getOrderLineList().get(index);
        return OrderLine.create(
                OrderedMenuInfo.create(
                        list.getMenuId(),
                        menuService.getMenuInfo(list.getMenuId()).getTitle(),
                        menuService.getMenuInfo(list.getMenuId()).getPrice()
                ),
                Count.create(list.getCount()));
    }


}
