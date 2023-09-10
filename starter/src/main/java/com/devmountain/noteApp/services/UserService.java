package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    //handle registering a User
    @Transactional
    List<String> addUser(UserDto userDto);

    // handle logging a User into our application
    List<String> userLogin(UserDto userDto);
}
