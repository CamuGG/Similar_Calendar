package com.example.calendar.service;


import com.example.calendar.model.Event;
import com.example.calendar.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;



    public void createEvent(Event event){
        eventRepository.save(event);
    }


    public List<Event> viewEvents(){
        return eventRepository.findAll();
    }


    @Transactional
    public void updateEvent(int id, Optional<String> name, Optional<String> description, Optional<LocalDateTime> toStart, Optional<LocalDateTime> theEnd){

        Event event = eventRepository.getById(id);

        if (event != null){
            name.ifPresent(event::setName);
            description.ifPresent(event::setDescription);
            toStart.ifPresent(event::setToStart);
            theEnd.ifPresent(event::setTheEnd);

            eventRepository.updateEvent(id, event.getName(), event.getDescription(), event.getToStart(), event.getTheEnd());
        }
    }


    public void deleteEvent(int id){
        eventRepository.deleteById(id);
    }
}
