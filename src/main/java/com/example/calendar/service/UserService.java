package com.example.calendar.service;


import com.example.calendar.model.Calendar;
import com.example.calendar.model.Event;
import com.example.calendar.model.User;
import com.example.calendar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public void createUser(User user){
        userRepository.save(user);
    }


    public List<User> viewUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void updateUser(int id,
                            Optional<String> name,
                            Optional<String> surname,
                            Optional<String> password) {

        User user = userRepository.getById(id);

        if (user != null){
            name.ifPresent(user::setName);
            surname.ifPresent(user::setSurname);
            password.ifPresent(user::setPassword);

            userRepository.updateUser(id,
                    user.getName(),
                    user.getSurname(),
                    user.getPassword());
        }
    }

    public void deleteUser(int id) throws UserPrincipalNotFoundException {

        User user = userRepository.getById(id);

        if (user == null) {
            throw new UserPrincipalNotFoundException("User with ID " + id + " not found");
        }

        userRepository.deleteById(id);
    }

}
