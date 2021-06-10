package com.project2.demo.interest.domain.FoodInterest;


import com.project2.demo.interest.domain.PreferredId;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class FoodInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PreferredId preferredId;

    @Column(name = "food_preference")
    @Convert(converter = FoodPreferenceConverter.class)
    private FoodPreference foodPreference;


}
