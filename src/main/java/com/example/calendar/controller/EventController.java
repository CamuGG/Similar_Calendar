package com.example.calendar.controller;

import com.example.calendar.model.Event;
import com.example.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/{calendarID}")
    public ResponseEntity createNewEvent(@PathVariable int calendarID,@RequestBody Event event){

        try {
            return ResponseEntity.ok(eventService.createEvent(calendarID, event));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{calendarID}")
    public ResponseEntity viewAllEventsToCalendar(@PathVariable int calendarID){

        try {
            return ResponseEntity.ok(eventService.viewEventsToCalendar(calendarID));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEvent(@PathVariable int id, @RequestBody Event event){

        try {
            return ResponseEntity.ok(eventService.updateEvent(id, event));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/{eventID}/user/{userID}")
    public ResponseEntity invitedUser(@PathVariable int eventID, @PathVariable int userID){

        try {
            return ResponseEntity.ok(eventService.inviteUser(eventID, userID));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvent(int id){

        try {
            return ResponseEntity.ok(eventService.deleteEvent(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
