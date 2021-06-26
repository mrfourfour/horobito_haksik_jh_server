package com.project2.demo.category.categorizedFood.domain;

import com.project2.demo.Menu.menu.service.MenuDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorizedFoodRepository extends JpaRepository<CategorizedFood, Long> {
    CategorizedFood findByCategoryIdAndMenuId(CategoryId categoryId, MenuId menuId);

    List<CategorizedFood> findAllByCategoryId(CategoryId categoryId);
}
