package com.project2.demo.Menu.category.controller;


import com.project2.demo.Menu.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public void createCategory(@RequestBody CategoryParameter categoryParameter){
        categoryService.create(categoryParameter);
    }

    @PostMapping("/{categoryId}/add/{foodId}")
    public void addFood(@PathVariable Long categoryId,
                            @PathVariable Long foodId){
        categoryService.addFood();
    }
}
