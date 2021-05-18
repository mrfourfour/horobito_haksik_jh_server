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
        Time time;
        if (!menuParameter.getLimited()){

            time = Time.create(
                    menuParameter.getStartTime(),
                    menuParameter.getEndTime()
            );
        }else {
            time = Time.limit(
                    menuParameter.getStartTime(),
                    menuParameter.getEndTime(),
                    menuParameter.getEndDay()
            );
        }



        Menu menu =
                Menu.create(
                        FoodName.create(menuParameter.getFoodName()
                        ),
                        Price.create(menuParameter.getPrice()),
                        time,
                        CategoryCountry.valueOf(menuParameter.getCountry()),
                        CategoryFood.valueOf(menuParameter.getFood())
                );
        menuRepository.save(menu);
    }
}
