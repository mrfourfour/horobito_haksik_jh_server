package com.project2.demo.Menu.menu.controller;


import com.project2.demo.Menu.menu.service.MenuDto;
import com.project2.demo.Menu.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuDto> getMenuInfo(@PathVariable Long menuId){
        try {
            return ResponseEntity.ok(menuService.getMenuInfo(menuId));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{menuId}/add/{amount}/")
    public ResponseEntity<Void> addAmount(@PathVariable Long menuId,
                                          @PathVariable int amount)
    {
        try {
            menuService.addAmount(menuId, amount);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException ie){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{menuId}/subtract/{amount}/")
    public ResponseEntity<Void> subtractAmount(@PathVariable Long menuId,
                                               @PathVariable int amount
    ){
        try {
            menuService.subtractAmount(menuId, amount);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException ie){
            return ResponseEntity.badRequest().build();
        }
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

    @PutMapping("/{menuId}/limit")
    public ResponseEntity<Void> setMenuLimited(@PathVariable Long menuId){
        try {
            menuService.limitMenu(menuId);
            return ResponseEntity.ok().build();
        }catch (DateTimeException | IllegalArgumentException de){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Void> unLimitMenu(@PathVariable Long menuId){
        try {
            menuService.unLimitMenu(menuId);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException argE){
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{menuId}")
    private ResponseEntity<Void> deleteMenu(@PathVariable Long menuId){
        try {
            menuService.deleteMenu(menuId);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException ie){
            return ResponseEntity.badRequest().build();
        }
    }



}
