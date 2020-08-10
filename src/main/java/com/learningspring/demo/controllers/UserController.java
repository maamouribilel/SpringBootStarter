package com.learningspring.demo.controllers;

import com.learningspring.demo.entities.User;
import com.learningspring.demo.exception.GlobalException;
import com.learningspring.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    // Autowire UserService
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get all users method
    @GetMapping("")
    @CrossOrigin(origins = "*")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    // create user method
    @PostMapping("")
    @CrossOrigin(origins = "*")
    public User createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
        try {
            return this.userService.createUser(user);
        } catch (GlobalException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    // get user by id method
    @GetMapping("/{id}")
    public Optional<User> getAllUsers(@PathVariable("id") @Min(0) Long id) {
        try {
            Optional<User> user = this.userService.getUserById(id);
            return user;
        } catch (GlobalException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // update user by id method
    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            return userService.updateUserById(id, user);
        } catch (GlobalException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // delete user by id method
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        try {
            this.userService.deleteUserById(id);
        } catch (GlobalException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    // find user by username method
    @GetMapping("/username/{username}")
    public Optional<User> findUserByUserName(@PathVariable String username) {
        try {
            return this.userService.findUserByUsername(username);
        } catch (GlobalException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
