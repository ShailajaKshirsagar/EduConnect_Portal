package com.educonnect.controller;

import com.educonnect.dto.UserRequestDto;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomUserService customUserService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userdto){
        String msg = customUserService.saveUser(userdto);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
