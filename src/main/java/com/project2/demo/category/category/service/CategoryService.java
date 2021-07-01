package com.project2.demo.category.category.service;

import com.project2.demo.Menu.menu.domain.Menu;
import com.project2.demo.Menu.menu.service.MenuDto;
import com.project2.demo.category.categorizedFood.domain.CategorizedFood;
import com.project2.demo.category.categorizedFood.domain.CategorizedFoodRepository;
import com.project2.demo.category.categorizedFood.domain.CategoryId;
import com.project2.demo.category.controller.CategoryParameter;
import com.project2.demo.category.category.domain.*;
import com.project2.demo.Menu.menu.domain.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final CategorizedFoodRepository categorizedFoodRepository;


    @Transactional
    public void create(CategoryParameter categoryParameter) {
        Category category = getCategory(categoryParameter);
        categoryRepository.save(category);
    }




    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        checkExistence(category);
        checkAlreadyDeleted(category);
        category.delete();
    }

    public CategoryDto get(Long categoryId) {
        Category category = getCategoryById(categoryId);
        checkExistence(category);
        checkAlreadyDeleted(category);
        return getCategoryDto(category);
    }

    public List<CategoryDetailDto> getAllDetails(Long cursor, Pageable page) {
        return getCategories(cursor, page)
                .stream().map(this::getCategoryDetailDto).collect(Collectors.toList());
    }

    public CursoredCategoryDetailDto getDetailInfo(Long categoryId, Long cursor, Pageable page) {
        checkExistence(categoryId);
        List<MenuDto> menuList = getMenus(categoryId, cursor,  page);
        return new CursoredCategoryDetailDto(menuList, hasNext(categoryId, getLastIndex(menuList)));
    }

    private Long getLastIndex(List<MenuDto> menuList) {
        return (menuList.isEmpty()) ? null : menuList.get(menuList.size()-1).getId();
    }

    @Transactional
    public void changeTitleAndDescription(Long categoryId, String title, String description) {
        checkExistence(categoryId);
        checkValidInput(title);
        checkValidInput(description);
        Category category = getCategoryById(categoryId);
        checkAlreadyDeleted(category);
        category.changeTitleAndDescription(title, description);
    }

    private CategoryDetailDto getCategoryDetailDto(Category category) {
        return new CategoryDetailDto(
                category.getId(),
                category.getCategoryName(),
                category.getDescription(),
                categorizedFoodRepository.findAllByCategoryIdAndDeleted(CategoryId.create(category.getId()), false).stream()
                        .map(categorizedFood-> menuRepository.findMenuById(categorizedFood.getCategoryId()))
                        .map(this::getMenuDto).collect(Collectors.toList())
        );
    }

    private void checkValidInput(String input) {
        if (input == null){
            throw new IllegalArgumentException();
        }
    }

    private void checkAlreadyDeleted(Category category) {
        if (category.checkDeleted()){
            throw new IllegalArgumentException();
        }
    }

    private CategoryDto getCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getCategoryName(),
                category.getDescription());
    }


    private Category getCategory(CategoryParameter categoryParameter) {
        return Category.create(
                CategoryName.create(categoryParameter.getCategoryName()),
                Description.create(categoryParameter.getDescription())
        );
    }




    private void checkExistence(Category category) {
        if (category==null){
            throw new IllegalArgumentException();
        }
    }

    public void checkExistence(Long categoryId) {
        if (categoryRepository.findCategoryById(categoryId)==null){
            throw new IllegalArgumentException();
        }
    }


    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findCategoryById(categoryId);
    }

    private boolean hasNext(Long categoryId, Long cursor) {
        if (cursor == null){
            return false;
        }
        return categorizedFoodRepository.existsByIdLessThanAndCategoryId(cursor, CategoryId.create(categoryId));
    }

    private MenuDto getMenuDto(Menu menu) {
        return new MenuDto(
                menu.getId(),
                menu.getTitle(),
                menu.getMenuDescription(),
                menu.getImageURL(),
                menu.getPrice(),
                menu.getSalesTime().getStartTime(),
                menu.getSalesTime().getEndTime()
                );
    }

    private List<MenuDto> getMenus(Long categoryId, Long cursor, Pageable page) {
        return cursor == null ?
                convertCategorizedFoodToMenuDto(categorizedFoodRepository.findAllByCategoryIdAndDeletedOrderByIdDesc( CategoryId.create(categoryId), false, page)) :
                convertCategorizedFoodToMenuDto(categorizedFoodRepository.findByIdLessThanAndCategoryIdAndDeletedOrderByIdDesc(cursor, CategoryId.create(categoryId), false, page));
    }

    private List<MenuDto> convertCategorizedFoodToMenuDto(List<CategorizedFood> allByCategoryIdOrderByIdDesc) {
        return allByCategoryIdOrderByIdDesc.stream().map(categorizedFood-> menuRepository.findMenuById(categorizedFood.getMenuId()))
                .map(this::getMenuDto).collect(Collectors.toList());
    }

    private List<Category> getCategories(Long cursor, Pageable page) {
        return cursor == null ? categoryRepository.findAllByDeletedOrderByIdDesc(false, page) :
                categoryRepository.findByIdLessThanAndDeletedOrderByIdDesc(cursor, false, page);
    }
}
