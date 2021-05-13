package com.project2.demo.product.menu;


import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.Clock;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.Date;

@Embeddable
@NoArgsConstructor
public class Time {

     MonthDay endDay;

     Boolean limited;

     LocalTime startTime;
     LocalTime endTime;

    private Time(LocalTime startTime, LocalTime endTime) {
        this.endDay = null;
        this.limited = false;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private Time(LocalTime startTime, LocalTime endTime, MonthDay limitPeriod) {
        this.endDay = limitPeriod;
        this.limited = true;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Time create(LocalTime startTime, LocalTime endTime){
        return new Time(startTime, endTime);
    }

    public static Time limit(LocalTime startTime, LocalTime endTime, MonthDay limitPeriod){
        return new Time(startTime, endTime);
    }


}
