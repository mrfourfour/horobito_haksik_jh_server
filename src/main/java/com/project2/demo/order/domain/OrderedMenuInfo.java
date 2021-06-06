package com.project2.demo.order.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OrderedMenuInfo {

    private Long menuId;

    private String menuName;

    private int price;
}
