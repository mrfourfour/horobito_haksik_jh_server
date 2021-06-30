package com.project2.demo.category.categorizedFood.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class MenuId {

    private Long menuId;

    private MenuId(Long menuId) {
        this.menuId = menuId;
    }

    public static MenuId create(Long menuId){
        return new MenuId(menuId);
    }
}
