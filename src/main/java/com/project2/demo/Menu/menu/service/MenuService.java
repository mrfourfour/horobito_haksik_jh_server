package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.controller.LimitDayParameter;
import com.project2.demo.Menu.menu.controller.MenuParameter;
import com.project2.demo.Menu.menu.domain.*;
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
    public void deleteMenu(Long menuId){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        menu.delete();
    }




    @Transactional
    public void limitMenu(Long menuId, LimitDayParameter limitDayParameter){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        checkAlreadyLimited(menu);
        menu.limit(MonthDay.of(limitDayParameter.getLimitedMonth(),
                limitDayParameter.getLimitedDayOfMonth()));

    }

    @Transactional
    public void unLimitMenu(Long menuId){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        checkAlreadyUnLimited(menu);
        menu.unLimit();
    }

    @Transactional
    public void setSoldOut(Long menuId){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        checkAlreadySoldOut(menu);
        menu.setSoldOut();

    }

    @Transactional
    public void setUnSoldOut(Long menuId){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        checkAlreadyUnSoldOut(menu);
        menu.setUnSoldOut();
    }



    @Transactional
    public void buy(Long menuId, int purchaseQuantity) {
        // 유저 관련 기능 아직 추가 안함
        Menu menu = getMenuById(menuId);
        menu.decreaseAmountOfFoodLeft(purchaseQuantity);
    }
    public void checkAlreadySoldOut(Menu menu) {
        if (menu.discriminateSoldOut()){
            throw new IllegalArgumentException();
        }
    }

    public void checkAlreadyUnSoldOut(Menu menu) {
        if (!menu.discriminateSoldOut()){
            throw new IllegalArgumentException();
        }
    }


    public void checkAlreadyUnLimited(Menu menu) {
        if (!menu.discriminateLimit()){
            throw new IllegalArgumentException();
        }
    }
    public void checkAlreadyLimited(Menu menu) {
        if (menu.discriminateLimit()){
            throw new IllegalArgumentException();
        }
    }

    public Menu getMenuById(Long menuId) {
        return menuRepository.findMenuByIdAndDeleted(menuId, false);
    }


    public void checkExistence(Menu menu) {
        if (menu==null){
            throw new IllegalArgumentException();
        }
    }


    public Menu getMenu(Time time, MenuParameter menuParameter) {
        Menu menu =Menu.create(
                FoodName.create(menuParameter.getFoodName()
                ),
                Price.create(menuParameter.getPrice()),
                time
        );
        return menu;
    }

    public Time getTime(MenuParameter menuParameter) {
        if (!menuParameter.isLimited()){

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
