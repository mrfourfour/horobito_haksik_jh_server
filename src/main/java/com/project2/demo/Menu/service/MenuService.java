package com.project2.demo.Menu.service;


import com.project2.demo.Menu.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
