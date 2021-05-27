package com.project2.demo.interest.domain;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class PreferenceFoodConverter implements AttributeConverter<PreferenceInfo, String> {


    @Override
    public String convertToDatabaseColumn(PreferenceInfo preferenceInfo) {
        if (preferenceInfo==null){
            return null;
        }
        return preferenceInfo.getGetPreferenceFoodIds()
                .stream()
                .map(value -> Long.toString(value))
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        if (dbData==null){
            return null;
        }
        return Arrays.stream(dbData.split(","))
                .map(value -> Long.parseLong(value))
                .collect(Collectors.toList());
    }
}
