package com.project2.demo.category.categorizedFood.domain;

import com.project2.demo.Menu.menu.service.MenuDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorizedFoodRepository extends JpaRepository<CategorizedFood, Long> {
    CategorizedFood findByCategoryIdAndMenuId(CategoryId categoryId, MenuId menuId);

    List<CategorizedFood> findAllByCategoryId(CategoryId categoryId);

    List<CategorizedFood> findAllByCategoryIdOrderByIdDesc(CategoryId categoryId, Pageable page);

    List<CategorizedFood> findByCategoryIdAndIdLessThanOrderByIdDesc(CategoryId categoryId, Long cursor, Pageable page);
}
