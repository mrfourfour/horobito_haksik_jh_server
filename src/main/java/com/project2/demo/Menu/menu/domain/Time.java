package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalTime;
import java.time.MonthDay;

@Embeddable
@NoArgsConstructor
@Getter
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
        return new Time(startTime, endTime, limitPeriod);
    }


}
