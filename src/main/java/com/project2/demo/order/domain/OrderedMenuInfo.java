package com.project2.demo.order.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class OrderedMenuInfo {

    private Long menuId;

    private String menuName;

    private int price;

    private OrderedMenuInfo(Long menuId, String menuName, int price) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
    }

    public static OrderedMenuInfo create(Long menuId, String menuName, int price){
        return new OrderedMenuInfo(menuId, menuName, price);
    }

}
