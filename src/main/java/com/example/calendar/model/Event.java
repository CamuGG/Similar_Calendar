package com.example.calendar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime toStart;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime theEnd;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    //private Set<User> userSet;



    public Event(){

    }

    public Event(String name, String description, LocalDateTime toStart, LocalDateTime theEnd) {
        this.name = name;
        this.description = description;
        this.toStart = toStart;
        this.theEnd = theEnd;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getToStart() {
        return toStart;
    }

    public void setToStart(LocalDateTime toStart) {
        this.toStart = toStart;
    }

    public LocalDateTime getTheEnd() {
        return theEnd;
    }

    public void setTheEnd(LocalDateTime theEnd) {
        this.theEnd = theEnd;
    }
}
