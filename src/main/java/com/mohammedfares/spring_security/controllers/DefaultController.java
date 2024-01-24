package com.mohammedfares.spring_security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/default")
public class DefaultController {
    @GetMapping
    public ResponseEntity<String> index(){
        return new ResponseEntity<>("Hello Fares !", HttpStatus.OK);
    }
}
