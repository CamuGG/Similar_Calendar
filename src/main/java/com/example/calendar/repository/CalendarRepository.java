package com.example.calendar.repository;

import com.example.calendar.model.Calendar;
import com.example.calendar.model.Event;
import com.example.calendar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    @Modifying
    @Query("UPDATE Calendar c SET c.name = :name, " +
            "c.description = :description, " +
            "c.user = :user " +
            "WHERE c.id = :id")
    void updateCalendar(@Param("id") int id,
                        @Param("name") String name,
                        @Param("description") String description,
                        @Param("user")User user);

}
