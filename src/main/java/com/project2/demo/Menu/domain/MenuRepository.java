package com.project2.demo.Menu.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuById(Long id);

    Menu findMenuByIdAndDeleted(Long id, boolean deleted);
}
