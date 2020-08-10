package com.learningspring.demo.controllers;

import com.learningspring.demo.entities.Order;
import com.learningspring.demo.entities.User;
import com.learningspring.demo.repositories.OrderRepository;
import com.learningspring.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/hateoas/users")
public class OrderHateoasController {
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public OrderHateoasController(UserRepository userRepository, OrderRepository orderRepository){
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
    }

    // get all orders for a user
    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User userExist = userOptional.get();
            return userExist.getOrders();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist.");
        }
    }
}
