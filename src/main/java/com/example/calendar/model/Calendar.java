package com.example.calendar.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Calendar {

    @Id
    @GeneratedValue
    private int id;

    private String name;
/*
    @ManyToOne
    @JoinColumn(name = "owner_calendar_id")
    private User user;

 */

    @OneToMany(mappedBy = "calendar")
    private List<Event> eventList;


    public Calendar(){

    }


    public Calendar(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

 */

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
