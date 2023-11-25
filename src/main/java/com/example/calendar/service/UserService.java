package com.example.calendar.service;


import com.example.calendar.model.Calendar;
import com.example.calendar.model.User;
import com.example.calendar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public User createUser(User user) throws Exception{
        try {
            Calendar calendar = new Calendar();
            calendar.setName("default calendar");
            calendar.setUser(user);

            user.getCalendars().add(calendar);

            return userRepository.save(user);
        } catch (Exception e){
            throw new Exception(String.format("Email %s already is use", user.getEmail()));
        }

    }


    public List<User> viewUsers() throws Exception{
        if (userRepository.findAll().isEmpty()){
            return userRepository.findAll();
        } else {
            throw new Exception("Users not found");
        }

    }


    public Optional<User> viewUserToId(int id) throws Exception{
        if (userRepository.findById(id).isPresent()){
            return userRepository.findById(id);
        } else {
            throw new Exception(String.format("User with ID %s not found", id));
        }

    }


    public Optional<User> viewUserByEmail(String email) throws Exception{

        try {
            return userRepository.findByEmail(email);
        } catch (Exception e){
            throw new Exception(String.format("User with email %s not found", email));
        }

    }



    public User updateUser(int id, User updateUser) throws Exception{
        if (userRepository.findById(id).isPresent()){

            User user = userRepository.findById(id).get();

            if (Objects.nonNull(updateUser.getName())){
                user.setName(updateUser.getName());
            }

            if (Objects.nonNull(updateUser.getSurname())){
                user.setSurname(updateUser.getSurname());
            }

            if (Objects.nonNull(updateUser.getPassword())){
                user.setPassword(updateUser.getPassword());
            }

            return userRepository.save(user);
        } else {
            throw new Exception(String.format("User with ID %s not found", id));
        }
    }

    public String deleteUser(int id) throws Exception{
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return String.format("User with ID %s deleted", id);
        } else {
            throw new Exception(String.format("User with ID %s not exist", id));
        }

    }

}
