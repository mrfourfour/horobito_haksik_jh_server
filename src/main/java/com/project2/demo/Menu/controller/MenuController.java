package com.project2.demo.Menu.controller;


import com.project2.demo.Menu.service.MenuService;
import com.project2.demo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;


    @GetMapping
    public void createMenu(@RequestBody MenuParameter menuParameter){
        menuService.createMenu(menuParameter);
    }


    @GetMapping("/{menuId}")
    public void limitMenu(@PathVariable Long menuId, @RequestBody LimitDayParameter limitDayParameter){
        menuService.limitMenu(menuId, limitDayParameter);
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
        menuService.setSoldOut(menuId);
    }

    @GetMapping("/{menuId}")
    public void setUnSoldOut(@PathVariable Long menuId){
        menuService.setUnSoldOut(menuId);
    }


}
