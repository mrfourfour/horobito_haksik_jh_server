package com.project2.demo.category.controller;


import com.project2.demo.category.categorizedFood.service.CategorizedFoodService;
import com.project2.demo.category.category.service.CategoryDetailDto;
import com.project2.demo.category.category.service.CursoredCategoryDetailDto;
import com.project2.demo.category.category.service.CategoryDto;
import com.project2.demo.category.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{categoryId}}")
    public ResponseEntity<CategoryDto> getCategoryInfo(@PathVariable Long categoryId){
        try {
            return ResponseEntity.ok(categoryService.getCategoryInfo(categoryId));
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

    @GetMapping("/details")
    public ResponseEntity<List<CategoryDetailDto>> getAllDetails(
            @RequestParam(value = "cursor") Long cursor,
            @RequestParam(value = "size") int size
    ){
        try {
            return ResponseEntity.ok(categoryService.getAllDetails(cursor, PageRequest.of(0, size)));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{categoryId}/details") // 7. 특정 카테고리 세부 조회
    public ResponseEntity<CursoredCategoryDetailDto> getDetailCategoryInfo(
            @PathVariable Long categoryId,
            @RequestParam(value = "cursor") Long cursor,
            @RequestParam(value = "size") int size
    ){
        try {
            return ResponseEntity.ok(categoryService.getDetailInfo(categoryId, cursor, PageRequest.of(0, size)));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> changeTitleAndDescription(
            @PathVariable Long categoryId,
            @RequestBody ChangeInfo changeInfo
    ){
        try {
            categoryService.changeTitleAndDescription(
                    categoryId, changeInfo.getTitle(), changeInfo.getDescription());
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Value
    private class ChangeInfo {
        String title;
        String description;
    }
}
