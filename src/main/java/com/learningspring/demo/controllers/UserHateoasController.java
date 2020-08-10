package com.learningspring.demo.controllers;

import com.learningspring.demo.entities.User;
import com.learningspring.demo.exception.GlobalException;
import com.learningspring.demo.repositories.UserRepository;
import com.learningspring.demo.services.UserService;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;

//import javax.validation.constraints.Min;
import java.util.List;
//import java.util.Optional;

@RestController
@Validated
@RequestMapping("/hateoas/users")
public class UserHateoasController {
    private UserRepository userRepository;
    private UserService userService;

    public UserHateoasController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // get all users method
    @GetMapping("")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    // get user by id method
    /*
    @GetMapping("/{id}")
    public EntityModel<User> getAllUsers(@PathVariable("id") @Min(0) Long id) {
        try {
            Optional<User> userOptional = this.userService.getUserById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Long userId = user.getId();
                Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
                user.add(selfLink);
                EntityModel<User> finalResource = new EntityModel<User>(user);
                return finalResource;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user do not exist");
            }

        } catch (GlobalException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }


    }
 */
}
