package com.project2.demo.Menu.menu.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuById(Long id);

    Menu findMenuByIdAndDeleted(Long id, boolean deleted);

    Menu findMenuByTitle(Title title);
}
