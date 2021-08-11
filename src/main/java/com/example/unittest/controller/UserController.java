package com.example.unittest.controller;

import com.example.unittest.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/getUser")
    public User getUser(){
        //codes
        return User.builder().id(1).name("Mert").lastName("Çakmak").build();
    }

    @PostMapping("/saveUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user){
        //codes
        return new User(1,"Mert", "Çakmak");
    }

    @DeleteMapping("/deleteUser")
    public int deleteUser(@RequestParam int id){
         // codes
        return id;
    }
}
