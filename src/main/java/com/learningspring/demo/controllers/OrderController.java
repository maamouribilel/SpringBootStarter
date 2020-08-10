package com.learningspring.demo.controllers;

import com.learningspring.demo.entities.Order;
import com.learningspring.demo.entities.User;
import com.learningspring.demo.repositories.OrderRepository;
import com.learningspring.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/users")
public class OrderController {
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public OrderController(UserRepository userRepository, OrderRepository orderRepository) {
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

    // create order method
    @PostMapping("/{userId}/orders")
    public Order createOrder(@PathVariable Long userId, @RequestBody Order order) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User userExist = userOptional.get();
            order.setUser(userExist);
            return orderRepository.save(order);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist.");
        }
    }

    // get order by order id
    @GetMapping("/orders/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderOptional;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no order with this id: " + id);
        }
    }

    // get order by id and userId
    @GetMapping("/{userId}/orders/{orderId}")
    public Order getOrderByIdByUser(@PathVariable Long userId, @PathVariable Long orderId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User userExist = userOptional.get();
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()){
                Order orderExist = orderOptional.get();
                if (orderExist.getUser().getId() == userId){
                    return orderExist;
                }else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user => "+ userId + " doesn\'t own this order => " + orderId);
                }

            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no order with this id: " + orderId);
            }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no user with this id: " + userId);
        }
        }

}
