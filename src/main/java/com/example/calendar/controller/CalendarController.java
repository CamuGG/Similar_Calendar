package com.example.calendar.controller;

import com.example.calendar.model.Calendar;
import com.example.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {

    @Autowired
    CalendarService calendarService;


    @PostMapping("/create-new-calendar")
    public String createNewCalendar(@RequestBody Calendar calendar){
        calendarService.createCalendar(calendar);
        return "New calendar created";
    }

    @GetMapping("/view-all-calendar")
    public List<Calendar> viewAllCalendar(){
       return calendarService.getAllCalendar();
    }

    @PutMapping("/update-calendar")
    public String updateCalendar(@RequestParam int id, @RequestBody Calendar calendar){
        calendarService.updateCalendar(id,  calendar.getName(), calendar.getDescription());
        return "Calendar updated";
    }

    @DeleteMapping("/delete-calendar")
    public String deleteCalendar(@RequestParam int id){
        calendarService.deleteCalendar(id);
        return "Calendar with id " +  id + " deleted";
    }

}
