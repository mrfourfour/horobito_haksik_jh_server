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

     LocalDateTime startTime;
     LocalDateTime endTime;



    private Time(LocalDateTime startTime, LocalDateTime endTime, boolean limit) {
        this.limited = true;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private Time(LocalDateTime startTime, LocalDateTime endTime) {
        this.limited = true;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Time create(LocalDateTime startTime, LocalDateTime endTime){
        return new Time(startTime, endTime);
    }

    public  static Time limit(LocalDateTime startTime, LocalDateTime endTime) {
        return new Time(startTime, endTime,true);
    }


}
