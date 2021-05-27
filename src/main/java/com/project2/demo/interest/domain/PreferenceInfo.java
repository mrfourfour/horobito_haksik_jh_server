package com.project2.demo.interest.domain;


import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class PreferenceInfo {


    private List<String> preferenceTypes;

    private List<String> getPreferenceFoods;
}
