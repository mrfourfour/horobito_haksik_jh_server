package com.project2.demo.Menu.category.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    private CategoryName categoryName;

    @Embedded
    private Description description;

    @ElementCollection
    @CollectionTable(
            name = "foodo_id",
            joinColumns = @JoinColumn(name = "category_id")
    )
    private Set<FoodId> foodIds;


}
