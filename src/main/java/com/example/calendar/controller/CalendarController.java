package com.example.calendar.controller;

import com.example.calendar.model.Calendar;
import com.example.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    CalendarService calendarService;


    @PostMapping("/{userID}")
    public ResponseEntity createNewCalendar(@PathVariable int userID, @RequestBody Calendar calendar){
        try {
            return ResponseEntity.ok(calendarService.createCalendar(userID, calendar));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/{userID}")
    public ResponseEntity viewAllCalendarByUserId(@PathVariable int userID){
       try {
           return ResponseEntity.ok(calendarService.getAllCalendarByUser(userID));
       } catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCalendar(@PathVariable int id, @RequestBody Calendar calendar){
        try {
            return ResponseEntity.ok(calendarService.updateCalendar(id, calendar));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCalendar(@PathVariable int id){
        try {
            return ResponseEntity.ok(calendarService.deleteCalendar(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
