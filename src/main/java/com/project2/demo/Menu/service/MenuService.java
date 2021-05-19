package com.project2.demo.Menu.service;


import com.project2.demo.Menu.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.MonthDay;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;


    @Transactional
    public void CreateMenu(MenuParameter menuParameter){

        Time time = getTime(menuParameter);
        Menu menu = getMenu(time, menuParameter);
        menuRepository.save(menu);
    }

    @Transactional
    public void limitMenu(Long menuId, MonthDay limitDay){
        Menu menu = getMenuById(menuId);
        menu.limit(limitDay);

    }

    @Transactional
    public void unLimitMenu(Long menuId){
        Menu menu = getMenuById(menuId);
        menu.unLimit();

    }

    @Transactional
    public void setSoldOut(Long menuId){
        Menu menu = getMenuById(menuId);
        menu.setSoldOut();

    }
    @Transactional
    public void setUnSoldOut(Long menuId){
        Menu menu = getMenuById(menuId);
        menu.setUnSoldOut();

    }

    private Menu getMenuById(Long menuId) {
        return menuRepository.findMenuById(menuId);
    }



    private Menu getMenu(Time time, MenuParameter menuParameter) {
        Menu menu =Menu.create(
                FoodName.create(menuParameter.getFoodName()
                ),
                Price.create(menuParameter.getPrice()),
                time,
                CategoryCountry.valueOf(menuParameter.getCountry()),
                CategoryFood.valueOf(menuParameter.getFood())
        );
        return menu;
    }

    private Time getTime(MenuParameter menuParameter) {
        if (!menuParameter.getLimited()){

            return Time.create(
                    menuParameter.getStartTime(),
                    menuParameter.getEndTime()
            );
        }else {
            return Time.limit(
                    menuParameter.getStartTime(),
                    menuParameter.getEndTime(),
                    menuParameter.getEndDay()
            );
        }
    }
}
