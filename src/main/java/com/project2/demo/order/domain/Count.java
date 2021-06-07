package com.project2.demo.order.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Count {

    private int count;

    private Count(int count) {
        this.count = count;
    }

    public static Count create(int count){
        return new Count(count);
    }
}
