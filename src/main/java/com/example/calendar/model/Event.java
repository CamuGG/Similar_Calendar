package com.example.calendar.model;

import com.example.calendar.util.RecurrenceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime toStart;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime theEnd;

    private RecurrenceType recurrenceType;

    @JsonIgnore
    private Integer recurrenceInterval;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    @JsonIgnore
    private Calendar calendar;

    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private List<User> users;



    public Event(){

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


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Calendar getCalendar() {
        return calendar;
    }


    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }


    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(RecurrenceType recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public Integer getRecurrenceInterval() {
        return recurrenceInterval;
    }

    public void setRecurrenceInterval(Integer recurrenceInterval) {
        this.recurrenceInterval = recurrenceInterval;
    }
}
