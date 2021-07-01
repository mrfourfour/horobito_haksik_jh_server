package com.project2.demo.category.categorizedFood.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorizedFoodRepository extends JpaRepository<CategorizedFood, Long> {
    CategorizedFood findByCategoryIdAndMenuId(CategoryId categoryId, MenuId menuId);

    List<CategorizedFood> findAllByCategoryIdAndDeleted(CategoryId categoryId, boolean deleted);

    List<CategorizedFood> findAllByCategoryIdAndDeletedOrderByIdDesc(CategoryId categoryId, boolean deleted, Pageable page);

    List<CategorizedFood> findByIdLessThanAndCategoryIdAndDeletedOrderByIdDesc(Long cursor, CategoryId categoryId, boolean deleted, Pageable page);

    boolean existsByIdLessThanAndCategoryId(Long cursor, CategoryId categoryId);
}
