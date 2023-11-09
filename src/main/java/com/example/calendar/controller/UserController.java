package com.example.calendar.controller;


import com.example.calendar.model.User;
import com.example.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/create-new-user")
    public String createNewUser(@RequestBody User user){

        userService.createUser(user);

        return "New user created";
    }

    @GetMapping("/view-all-user")
    public List<User> geAllUser() {
        return userService.viewUsers();
    }


    @PutMapping("/update-user")
    public String updateUserPassword(@RequestParam int id, @RequestBody User user) {

        userService.updateUser(id,
                Optional.ofNullable(user.getName()),
                Optional.ofNullable(user.getSurname()),
                Optional.ofNullable(user.getPassword()));

        return "User with ID " + id + " updated";
    }

    @DeleteMapping("/delete-user-by-id")
    public String deleteUserById(@RequestParam int id) throws UserPrincipalNotFoundException{

        userService.deleteUser(id);

        return "User with ID " + id + " deleted";
    }
}
