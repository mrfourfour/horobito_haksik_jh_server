package com.project2.demo.Menu.menu.controller;


import com.project2.demo.Menu.menu.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.MonthDay;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;


    @PostMapping
    public void createMenu(@RequestBody MenuParameter menuParameter){
        try {
            checkTimeValidity(menuParameter);
            menuService.createMenu(menuParameter);
        }catch (DateTimeException de){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Time error");
        }
    }

    @PostMapping("/{menuId}/buy/{purchaseQuantity}")
    public ResponseEntity<Void> buyMenus(@PathVariable Long menuId,
                         @PathVariable int purchaseQuantity){
        try {
            menuService.buy(menuId, purchaseQuantity);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping("/{menuId}")
    public void setMenuLimited(@PathVariable Long menuId, @RequestBody LimitDayParameter limitDayParameter){
        try {
            checkTimeValidity(limitDayParameter);
            menuService.limitMenu(menuId, limitDayParameter);
        }catch (DateTimeException de){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Time error");
        }catch (IllegalArgumentException argE ){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{menuId}")
    private void deleteMenu(@PathVariable Long menuId){
        menuService.deleteMenu(menuId);
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
    public ResponseEntity<Void> unLimitMenu(@PathVariable Long menuId){
        try {
            menuService.unLimitMenu(menuId);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException argE){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
