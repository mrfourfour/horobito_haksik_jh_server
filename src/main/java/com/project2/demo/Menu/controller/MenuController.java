package com.project2.demo.Menu.controller;


import com.project2.demo.Menu.service.MenuService;
import com.project2.demo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;


    @GetMapping
    public void createMenu(@RequestBody MenuParameter menuParameter){
        menuService.createMenu(menuParameter);
    }


}
