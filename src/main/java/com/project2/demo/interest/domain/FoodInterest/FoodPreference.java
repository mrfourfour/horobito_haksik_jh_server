package com.project2.demo.interest.domain.FoodInterest;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Converter;
import javax.persistence.Embeddable;
import java.util.HashSet;
import java.util.Set;


@Getter
@NoArgsConstructor
public class FoodPreference {


    Set<FoodName> preferredFoods = new HashSet<>();

    FoodPreference(Set<FoodName> preferredFoods){
        this.preferredFoods = preferredFoods;
    }

    public static FoodPreference create(Set<FoodName> preferredFoods){
        return new FoodPreference(preferredFoods);
    }
}
