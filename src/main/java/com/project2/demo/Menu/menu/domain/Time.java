package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;

@Embeddable
@NoArgsConstructor
@Getter
public class Time {


     Boolean limited;

     LocalTime startTime;
     LocalTime endTime;



    private Time(LocalTime startTime, LocalTime endTime, boolean limit) {
        this.limited = limit;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private Time(LocalTime startTime, LocalTime endTime) {
        this.limited = false;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Time create(LocalTime startTime, LocalTime endTime){
        return new Time(startTime, endTime);
    }

    public  static Time limit(LocalTime startTime, LocalTime endTime) {
        return new Time(startTime, endTime,true);
    }


}
