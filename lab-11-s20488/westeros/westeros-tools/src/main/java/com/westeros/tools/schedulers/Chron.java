package com.westeros.tools.schedulers;

import com.westeros.tools.schedulers.abstractions.IProvideNextExecutionTime;
import lombok.Builder;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.time.LocalDateTime;

public class Chron {

    private LocalDateTime start = LocalDateTime.now();
    private LocalDateTime end;
    private int maxCount =-1;
    private Duration interval = Duration.ofSeconds(1);
    private int counter = 0;

    @Builder
    Chron(LocalDateTime start, LocalDateTime end, int maxCount, Duration interval) {
        this.start = start;
        this.end = end;
        this.maxCount = maxCount;
        this.interval = interval;
    }

    public IProvideNextExecutionTime buildNextTimeExecutionProvider() {
        return ()->getNextTime();
    }

    private LocalDateTime getNextTime() {
        if(end!=null && end.isBefore(start))
            return null;
        if(maxCount>0&&this.counter>maxCount) return null;
        if(counter==0){
            counter++;
            return start;
        }
        counter++;
        start=start.plus(interval);
        return start;
    }
}
