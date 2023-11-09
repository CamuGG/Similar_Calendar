package com.example.calendar.service;

import com.example.calendar.model.Calendar;
import com.example.calendar.model.User;
import com.example.calendar.repository.CalendarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CalendarService {

    @Autowired
    CalendarRepository calendarRepository;

    public void createCalendar(Calendar calendar){
        calendarRepository.save(calendar);
    }

    public List<Calendar> getAllCalendar(){
        return calendarRepository.findAll();
    }

    @Transactional
    public void updateCalendar(int id, String name, String description) {

        Calendar calendar = calendarRepository.getById(id);
        if (calendar != null) {
            calendar.setName(name);
            calendar.setDescription(description);

            calendarRepository.updateCalendar(id, name,description);
        }

    }
    /*
    public void updateCalendar(int id,Calendar newCalendar){

        calendarRepository.updateCalendar(id, newCalendar.getName(), newCalendar.getDescription(), newCalendar.getUser());

        Calendar oldCalendar = calendarRepository.findById(id).orElse(null);
    }

     */

    public void deleteCalendar(int id){
        calendarRepository.deleteById(id);
    }

}
