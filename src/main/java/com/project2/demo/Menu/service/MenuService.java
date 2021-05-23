package com.project2.demo.Menu.service;


import com.project2.demo.Menu.controller.LimitDayParameter;
import com.project2.demo.Menu.controller.MenuParameter;
import com.project2.demo.Menu.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.MonthDay;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;


    @Transactional
    public void createMenu(MenuParameter menuParameter){

        Time time = getTime(menuParameter);
        Menu menu = getMenu(time, menuParameter);
        menuRepository.save(menu);
    }

    @Transactional
    public void limitMenu(Long menuId, LimitDayParameter limitDayParameter){

        Menu menu = getMenuById(menuId);

        checkExistence(menu);

        checkAlreadyLimited(menu);

        menu.limit(MonthDay.of(limitDayParameter.getLimitedMonth(),
                limitDayParameter.getLimitedDayOfMonth()));

    }

    private void checkAlreadyLimited(Menu menu) {
        if (menu.discriminateLimit()){
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void unLimitMenu(Long menuId){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);

        if (menu.discriminateSoldOut()){
            menu.unLimit();
        }else {
            throw new IllegalArgumentException();
        }

    }

    @Transactional
    public void setSoldOut(Long menuId){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);

        menu.setSoldOut();

    }

    @Transactional
    public void setUnSoldOut(Long menuId){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);

        menu.setUnSoldOut();

    }
    private Menu getMenuById(Long menuId) {
        return menuRepository.findMenuById(menuId);
    }


    private void checkExistence(Menu menu) {
        if (menu==null){
            throw new IllegalArgumentException();
        }
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
                    LocalTime.of(menuParameter.getStartHour(),  menuParameter.getStartMinute()),
                    LocalTime.of(menuParameter.getEndHour(), menuParameter.getEndMinute())
            );
        }else {
            return Time.limit(
                    LocalTime.of(menuParameter.getStartHour(),  menuParameter.getStartMinute()),
                    LocalTime.of(menuParameter.getEndHour(), menuParameter.getEndMinute()),
                    MonthDay.of(menuParameter.getEndMonth(), menuParameter.getEndDayOfMonth())
            );
        }
    }
}
