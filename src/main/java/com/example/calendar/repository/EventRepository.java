package com.example.calendar.repository;

import com.example.calendar.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {


    @Modifying
    @Query("UPDATE Event e SET e.name = :name, e.description = :description, e.toStart = :toStart, e.theEnd = :theEnd WHERE e.id = :id")
    void updateEvent(@Param("id") int id, @Param("name") String name, @Param("description") String description, @Param("toStart") LocalDateTime toStart, @Param("theEnd") LocalDateTime theEnd);





}
