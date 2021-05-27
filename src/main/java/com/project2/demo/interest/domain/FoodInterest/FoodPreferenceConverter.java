package com.project2.demo.interest.domain.FoodInterest;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class FoodPreferenceConverter implements AttributeConverter<FoodPreference, String> {
    @Override
    public String convertToDatabaseColumn(FoodPreference attribute) {
        if (attribute==null){
            return null;
        }
        return attribute.getPreferredFoods()
                .stream()
                .map(FoodName::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public FoodPreference convertToEntityAttribute(String dbData) {
        if (dbData==null){
            return null;
        }
        String[] foods = dbData.split(",");
        Set<FoodName> foodPreference
                = Arrays.stream(foods)
                .map(FoodName::create)
                .collect(Collectors.toSet());
        return new FoodPreference(foodPreference);
    }
}
