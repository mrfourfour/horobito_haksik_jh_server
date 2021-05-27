package com.project2.demo.interest.domain;


import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Getter
public class PreferenceInfo {


    private List<String> preferenceTypes;

    private List<Long> getPreferenceFoodIds;
}
