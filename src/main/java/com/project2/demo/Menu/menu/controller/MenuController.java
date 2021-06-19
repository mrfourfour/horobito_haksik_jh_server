package com.project2.demo.Menu.menu.controller;


import com.project2.demo.Menu.menu.service.MenuDto;
import com.project2.demo.Menu.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;


    @PostMapping("/make/default")
    public ResponseEntity<Void> createMenu(@RequestBody MenuParameter menuParameter){
        try {
            menuService.createMenu(menuParameter);
            return ResponseEntity.ok().build();
        }catch (DateTimeException de){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuDto> getMenuInfo(@PathVariable Long menuId){
        try {
            return ResponseEntity.ok(menuService.getMenuInfo(menuId));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{menuId}/limit")
    public void setMenuLimited(@PathVariable Long menuId){
        try {
            menuService.limitMenu(menuId);
        }catch (DateTimeException de){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Time error");
        }catch (IllegalArgumentException argE ){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{menuId}/add/{amount}/")
    public void addAmount(@PathVariable Long menuId,
                             @PathVariable int amount)
                             {
        menuService.addAmount(menuId, amount);
    }

    @PutMapping("/{menuId}/subtract/{amount}/")
    public void subtractAmount(@PathVariable Long menuId,
                             @PathVariable int amount
                             ){
        menuService.subtractAmount(menuId, amount);
    }

    @PutMapping("/{menuId}/amount/modify/{amount}/")
    public ResponseEntity<Void> modifyAmount(@PathVariable Long menuId,
                               @PathVariable int amount
    ){
        try {
            menuService.modifyAmount(menuId, amount);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping("/{menuId}")
    private void deleteMenu(@PathVariable Long menuId){
        menuService.deleteMenu(menuId);
    }



    @PutMapping("/{menuId}")
    public ResponseEntity<Void> unLimitMenu(@PathVariable Long menuId){
        try {
            menuService.unLimitMenu(menuId);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException argE){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @PutMapping("/{menuId}/soldout")
    public void setSoldOut(@PathVariable Long menuId){
        try {
            menuService.setSoldOut(menuId);
        }catch (IllegalArgumentException argE){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{menuId}/unsoldout")
    public void setUnSoldOut(@PathVariable Long menuId){
        try {
            menuService.setUnSoldOut(menuId);
        }catch (IllegalArgumentException argE){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        menuService.setUnSoldOut(menuId);
    }


}
