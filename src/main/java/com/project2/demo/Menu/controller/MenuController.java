package com.project2.demo.Menu.controller;


import com.project2.demo.Menu.service.MenuService;
import com.project2.demo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.MonthDay;

@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;


    @GetMapping
    public void createMenu(@RequestBody MenuParameter menuParameter){
        try {
            checkTimeValidity(menuParameter);
            menuService.createMenu(menuParameter);
        }catch (DateTimeException de){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{menuId}")
    public void limitMenu(@PathVariable Long menuId, @RequestBody LimitDayParameter limitDayParameter){
        try {
            checkTimeValidity(limitDayParameter);
            menuService.limitMenu(menuId, limitDayParameter);
        }catch (DateTimeException | IllegalArgumentException de){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    private void checkTimeValidity(MenuParameter menuParameter) {
        try {
            LocalTime.of(menuParameter.getStartHour(),  menuParameter.getStartMinute());
            LocalTime.of(menuParameter.getEndHour(), menuParameter.getEndMinute());
        }catch (DateTimeException de){
            throw new IllegalArgumentException();
        }
    }

    private void checkTimeValidity(LimitDayParameter limitDayParameter) {
        try {
            MonthDay.of(limitDayParameter.getLimitedMonth(),
                    limitDayParameter.getLimitedDayOfMonth());
        }catch (DateTimeException de){
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/{menuId}")
    public void unLimitMenu(@PathVariable Long menuId){
        try {
            menuService.unLimitMenu(menuId);
        }catch (IllegalArgumentException argE){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/{menuId}")
    public void setSoldOut(@PathVariable Long menuId){
        try {
            menuService.setSoldOut(menuId);
        }catch (IllegalArgumentException argE){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{menuId}")
    public void setUnSoldOut(@PathVariable Long menuId){
        try {
            menuService.setUnSoldOut(menuId);
        }catch (IllegalArgumentException argE){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        menuService.setUnSoldOut(menuId);
    }


}
