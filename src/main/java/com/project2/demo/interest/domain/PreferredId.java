package com.project2.demo.interest.domain;


import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class PreferredId {

    private String preferredId;

    private PreferredId(String preferredId) {
        this.preferredId = preferredId;
    }

    public static PreferredId create(String preferredId){
        return new PreferredId(preferredId);
    }
}
