package com.example.calendar.util;

import com.example.calendar.model.Event;
import org.springframework.util.DigestUtils;

import java.time.Duration;
import java.time.LocalDateTime;

public class RecurrenceManager {

    public void setRecurrenceTimeAndDuration(Event event, LocalDateTime startTime, int duration){

        event.setToStart(startTime);
        event.setRecurrenceInterval(duration);

        switch (event.getRecurrenceType()){

            case DAILY:
                event.setTheEnd(startTime.plusDays(duration));
                break;
            case WEEKLY:
                event.setTheEnd(startTime.plusWeeks(duration));
                break;
            case MONTHLY:
                event.setTheEnd(startTime.plusMonths(duration));
                break;
            case YEARLY:
                event.setTheEnd(startTime.minusYears(duration));
                break;
        }
    }

}
