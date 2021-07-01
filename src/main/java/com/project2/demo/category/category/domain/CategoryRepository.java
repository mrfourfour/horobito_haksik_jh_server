package com.project2.demo.category.category.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long categoryId);

    List<Category> findAllByDeletedOrderByIdDesc(boolean deleted,Pageable page);

    List<Category> findByIdLessThanAndDeletedOrderByIdDesc(Long id, boolean deleted, Pageable page);
}
