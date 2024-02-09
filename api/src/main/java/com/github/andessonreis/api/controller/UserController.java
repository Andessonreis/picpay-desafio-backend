package com.github.andessonreis.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.andessonreis.api.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.github.andessonreis.api.domain.user.User;
import com.github.andessonreis.api.domain.user.dto.UserDTO;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> listUsers(){
        return this.userService.listUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") @NotNull Long id) throws Exception{
        var user = this.userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO){
        User newUser = userService.createUser(userDTO);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
