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
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //User getById(int id);


    @Modifying
    @Query("UPDATE User u SET u.name = :name, " +
            "u.surname = :surname, " +
            "u.password = :password " +
            "WHERE u.id = :id")
    void updateUser(@Param("id") int id,
                    @Param("name") String name,
                    @Param("surname") String surname,
                    @Param("password") String password);



    Optional<User> findByEmail(String email);
}

