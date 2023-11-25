package com.example.calendar.service;


import com.example.calendar.model.Calendar;
import com.example.calendar.model.Event;
import com.example.calendar.model.User;
import com.example.calendar.repository.CalendarRepository;
import com.example.calendar.repository.EventRepository;
import com.example.calendar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    UserRepository userRepository;



    public Event createEvent(int calendarID, Event event) throws Exception{

        if (calendarRepository.findById(calendarID).isPresent()){
            Calendar calendar = calendarRepository.findById(calendarID).orElse(null);

            event.setCalendar(calendar);
        }else {
            throw new Exception(String.format("Calendar with ID %s not found", calendarID));
        }

        return eventRepository.save(event);
    }


    public List<Event> viewEventsToCalendar(int calendarID) throws Exception{

        if (calendarRepository.findById(calendarID).isPresent()){
            Calendar calendar = calendarRepository.findById(calendarID).orElse(null);

            List<Event> events;
            events = calendar.getEvents();

            return events;

        } else {
            throw new Exception(String.format("Calendar with ID %s not exist", calendarID));
        }
    }



    public Event updateEvent(int id, Event updateEvent) throws Exception{

        if (eventRepository.findById(id).isPresent()){

            Event event = eventRepository.findById(id).get();

            if (Objects.nonNull(updateEvent.getName())){
                event.setName(updateEvent.getName());
            }

            if (Objects.nonNull(updateEvent.getDescription())){
                event.setDescription(updateEvent.getDescription());
            }

            if (Objects.nonNull(updateEvent.getToStart())){
                event.setToStart(updateEvent.getToStart());
            }

            if (Objects.nonNull(updateEvent.getTheEnd())){
                event.setTheEnd(updateEvent.getTheEnd());
            }

            return eventRepository.save(event);
        } else {
            throw new Exception(String.format("Event with ID %s not found", id));
        }

    }


    @Transactional
    public String inviteUser(int eventID, int userID) throws Exception{

        Event event = eventRepository.findById(eventID).get();
        User user = userRepository.findById(userID).get();

        if (event == null || user == null){

            throw new Exception("Event or user not found");
        } else {
            event.getUsers().add(user);
            user.getEvents().add(event);
            eventRepository.save(event);
            userRepository.save(user);
            return "User successfully invited ";
        }

    }


    public String deleteEvent(int id) throws Exception{

        if (eventRepository.findById(id).isPresent()){
            eventRepository.deleteById(id);
            return String.format("Event with ID %s deleted", id);
        } else {
            throw new Exception(String.format("Event with ID %s not found", id));
        }
    }
}
