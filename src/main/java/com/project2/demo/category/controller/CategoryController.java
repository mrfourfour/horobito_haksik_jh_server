package com.project2.demo.category.controller;


import com.project2.demo.category.categorizedFood.service.CategorizedFoodService;
import com.project2.demo.category.category.service.CategoryDto;
import com.project2.demo.category.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategorizedFoodService categorizedFoodService;

    @PostMapping
    public void createCategory(@RequestBody CategoryParameter categoryParameter){
        categoryService.create(categoryParameter);
    }

    @PostMapping("/{categoryId}/add/{menuId}")
    public void addFood(@PathVariable Long categoryId,
                            @PathVariable Long menuId){
        categorizedFoodService.addFoodInCategory(categoryId, menuId);
    }

    @GetMapping("/{categoryId}}")
    public ResponseEntity<CategoryDto> getCategoryInfo(@PathVariable Long categoryId){
        try {
            return ResponseEntity.ok(categoryService.getCategoryInfo(categoryId));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{categoryId}/{menuId}")
    public ResponseEntity<Void> deleteMenuInCategory(@PathVariable Long categoryId,
                              @PathVariable Long menuId){

        try {
            categorizedFoodService.deleteMenuInCategory(categoryId, menuId);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
