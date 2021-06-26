package com.project2.demo.category.category.service;

import com.project2.demo.Menu.menu.service.MenuDto;
import lombok.Value;

import java.util.List;
@Value
public class CategoryDetailDto {
    List<MenuDto> menuDtoList;
    boolean hasNext;

}
