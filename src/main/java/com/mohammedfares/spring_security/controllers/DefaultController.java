package com.mohammedfares.spring_security.controllers;

import com.mohammedfares.spring_security.dto.UserDto;
import com.mohammedfares.spring_security.models.UserRequest;
import com.mohammedfares.spring_security.models.UserResponse;
import com.mohammedfares.spring_security.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/default")
public class DefaultController {

    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<String> index(){
        return new ResponseEntity<>("Hello Fares !", HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public UserResponse post (@RequestBody UserRequest userRequest) {

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userRequest, userDto);

        UserDto createdUser = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createdUser, userResponse);

        return userResponse;

    }
}
