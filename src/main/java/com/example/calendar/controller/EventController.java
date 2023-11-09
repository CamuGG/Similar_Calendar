package com.example.calendar.controller;

import com.example.calendar.model.Event;
import com.example.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/create-new-event")
    public String createNewEvent(@RequestBody Event event){
        eventService.createEvent(event);
        return "New event created";
    }

    @GetMapping("/view-all-event")
    public List<Event> viewAllEvents(){
        return eventService.viewEvents();
    }

    @PutMapping("/update-event")
    public String updateEvent(@RequestParam int id, @RequestBody Event event){
        eventService.updateEvent(id, Optional.ofNullable(event.getName()), Optional.ofNullable(event.getDescription()), Optional.ofNullable(event.getToStart()), Optional.ofNullable(event.getTheEnd()));
        return "Event updated";
    }

    @DeleteMapping("/delete-event")
    public String deleteEvent(int id){
        eventService.deleteEvent(id);
        return "Event with id " + id + " deleted";
    }
}
