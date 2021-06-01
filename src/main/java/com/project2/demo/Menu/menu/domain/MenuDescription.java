package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@RequiredArgsConstructor
public class MenuDescription {

    String menuDescription;

    private MenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public static MenuDescription create(String menuDescription){
        return new MenuDescription(menuDescription);
    }
}
