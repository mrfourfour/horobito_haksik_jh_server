package com.project2.demo.Menu.menu.domain;

import com.project2.demo.Menu.menu.service.MenuDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuById(Long id);

    Menu findMenuByIdAndDeleted(Long id, boolean deleted);

    Menu findMenuByTitle(Title title);

    List<Menu> findAllByOrderByIdDesc(Pageable page);

    List<Menu> findByIdLessThanOrderByIdDesc(Long cursor, Pageable page);

    boolean existsByIdLessThan(Long cursor);
}
