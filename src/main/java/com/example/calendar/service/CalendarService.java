package com.example.calendar.service;

import com.example.calendar.model.Calendar;
import com.example.calendar.model.Event;
import com.example.calendar.model.User;
import com.example.calendar.repository.CalendarRepository;
import com.example.calendar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CalendarService {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    UserRepository userRepository;

    public Calendar createCalendar(int userID, Calendar calendar) throws Exception{
        if (userRepository.findById(userID).isPresent()){
            User user = userRepository.findById(userID).orElse(null);

            calendar.setUser(user);

        } else {
            throw new Exception(String.format("User with ID %s not exist", userID));
        }

        return calendarRepository.save(calendar);
    }

    public List<Calendar> getAllCalendarByUser(int userID) throws Exception{
        Optional<User> user = userRepository.findById(userID);

        if (user.isPresent()) {
            return new ArrayList<>(user.get().getCalendars());
        } else {
            throw new Exception(String.format("User with ID %s don't have calendars", userID));
        }
    }


    public Calendar updateCalendar(int id, Calendar updateCalendar) throws Exception{
        if (calendarRepository.findById(id).isPresent()){

            Calendar calendar = calendarRepository.findById(id).get();

            if (Objects.nonNull(updateCalendar.getName())){
                calendar.setName(updateCalendar.getName());
            }

            if (Objects.nonNull(updateCalendar.getDescription())){
                calendar.setDescription(updateCalendar.getDescription());
            }

            return calendarRepository.save(calendar);
        } else {
            throw new Exception(String.format("Calendar with ID %s not found", id));
        }
    }


    public String deleteCalendar(int id) throws Exception{
        if (calendarRepository.findById(id).isPresent()){
            calendarRepository.deleteById(id);
            return String.format("Calendar with ID %s deleted", id);
        } else {
            throw new Exception(String.format("Calendar with ID %s not exist", id));
        }
    }

}
