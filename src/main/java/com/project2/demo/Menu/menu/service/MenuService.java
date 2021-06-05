package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.controller.MenuParameter;
import com.project2.demo.Menu.menu.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public void limitMenu(Long menuId){
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        checkAlreadyLimited(menu);
        menu.limit();

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
    public void modifyAmount(Long menuId, int amount) {
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        changeAmount(menu, amount);
    }

    @Transactional
    public void addAmount(Long menuId, int amount) {
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        menu.increaseAmountOfFoodLeft(amount);
    }

    @Transactional
    public void subtractAmount(Long menuId, int amount) {
        Menu menu = getMenuById(menuId);
        checkExistence(menu);
        menu.decreaseAmountOfFoodLeft(amount);
        menu.checkSoldOut();
    }


    public void changeAmount(Menu menu, int amount) {
        menu.changeAmount(amount);
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
        return Menu.create(
                FoodName.create(menuParameter.getTitle()),
                Price.create(menuParameter.getPrice()),
                MenuDescription.create(menuParameter.getDescription()),
                time,
                AmountOfFoodLeft.create(menuParameter.getAmount()),
                ImageURL.create(menuParameter.getImageURL())
        );
    }

    public Menu getMenuLimited(Time time, MenuParameter menuParameter) {
        return Menu.create(
                FoodName.create(menuParameter.getTitle()),
                Price.create(menuParameter.getPrice()),
                MenuDescription.create(menuParameter.getDescription()),
                time,
                AmountOfFoodLeft.create(menuParameter.getAmount()),
                ImageURL.create(menuParameter.getImageURL())
        );
    }

    public Time getTime(MenuParameter menuParameter) {

            return Time.create(
                    menuParameter.getStartTime(),
                    menuParameter.getEndTime()
            );

    }


    public MenuDto getMenuInfo(Long menuId) {
        Menu menu = getMenuById(menuId);
        return getMenuDto(menu);
    }

    private MenuDto getMenuDto(Menu menu) {
        return new MenuDto(
                menu.getId(),
                menu.getFoodName(),
                menu.getMenuDescription(),
                menu.getImageURL(),
                menu.getPrice(),
        menu.getSalesTime().getStartTime(),
        menu.getSalesTime().getEndTime());
    }
}
