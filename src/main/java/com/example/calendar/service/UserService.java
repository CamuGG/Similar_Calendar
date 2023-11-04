package com.example.calendar.service;

import com.example.calendar.DTO.UserDTO;
import com.example.calendar.model.User;
import com.example.calendar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO mapUserDTO(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }



    public void createUser(User user){
        userRepository.save(user);
    }


    public UserDTO getUserById(int id) throws UserPrincipalNotFoundException {

        User user = userRepository.getById(id);

        if (user == null){
            throw new UserPrincipalNotFoundException("User with ID " + id + " not found");
        }
        UserDTO userDTO = mapUserDTO(user);

        return userDTO;
    }

    public User getUserByEmail(String email) throws UserPrincipalNotFoundException {

        User user = userRepository.getByEmail(email);

        if (user == null){
            throw new UserPrincipalNotFoundException("User with email " + email + " not found");
        }
        return user;
    }

    @Transactional
    public void updatePassword(int id, String newPassword) throws UserPrincipalNotFoundException {

        User user = userRepository.getById(id);

        if (user == null){
            throw new UserPrincipalNotFoundException("User with ID " + id + " not found");
        }
        user.setPassword(newPassword);

        userRepository.updateUserPassword(id,newPassword);
    }

    public void deleteUserById(int id) throws UserPrincipalNotFoundException {

        User user = userRepository.getById(id);

        if (user == null) {
            throw new UserPrincipalNotFoundException("User with ID " + id + " not found");
        }

        userRepository.deleteById(id);
    }

}
