package com.devmountain.noteApp.controllers;
import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Autowire in dependencies, which are the UserService because Controller interact with ServiceLayers and the PasswordEncoder so that we can have incoming passwords.
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //UserController should have 2 methods corresponding to the 2 methods available on UserService
    //create a method that will handle POST request to be able to register a User.
    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto){
        String passHash = passwordEncoder.encode(userDto.getPassword()); //passwordEncoder method from Config class
        userDto.setPassword(passHash); // invoke the setPassword() on userDto and pass in the passHash
        return userService.addUser((userDto));
    }

    //create method that will handle POST request to be able to login a User.
    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }
}
