package com.example.calendar.controller;

import com.example.calendar.DTO.UserDTO;
import com.example.calendar.model.User;
import com.example.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/create-new-user")
    public String createNewUser(@RequestBody User user){
        userService.createUser(user);
        return "New user created";
    }

    @GetMapping("/view-user-by-id")
    public UserDTO getUserById(@RequestParam int id) throws UserPrincipalNotFoundException{
        return userService.getUserById(id);
    }


    @GetMapping("/view-user-by-email")
    public User getUserByEmail(@RequestParam String email) throws UserPrincipalNotFoundException{
        return userService.getUserByEmail(email);
    }

    @PutMapping("/update-user-password")
    public void updateUserPassword(@RequestParam int id, @RequestParam String password) throws UserPrincipalNotFoundException{
        userService.updatePassword(id, password);
    }

    @DeleteMapping("/delete-user-by-id")
    public void deleteUserById(@RequestParam int id) throws UserPrincipalNotFoundException{
        userService.deleteUserById(id);
    }
}
