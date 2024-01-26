package com.picpaysimplified.controllers;

import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.dtos.UserDTO;
import com.picpaysimplified.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDTO dto){
    User user = userService.createUser(dto);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users =  this.userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
