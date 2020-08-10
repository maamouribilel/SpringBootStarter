package com.learningspring.demo.services;

import com.learningspring.demo.entities.User;
import com.learningspring.demo.exception.GlobalException;
import com.learningspring.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // Autowire UserRepository
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users method
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // create new user method
    public User createUser(User user) throws GlobalException {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new GlobalException("This username already exits");
        } else {
            return userRepository.save(user);
        }

    }

    // get user by id method
    public Optional<User> getUserById(Long id) throws GlobalException {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user;
        } else {
            throw new GlobalException("There is no user with this ID");
        }
    }

    // update user by id method
    public User updateUserById(Long id, User user) throws GlobalException {
        user.setId(id);
        if (userRepository.findById(id).isPresent()) {
            return userRepository.save(user);
        } else {
            throw new GlobalException("Update - There is no user with this ID");
        }

    }

    // delete user by id method
    public void deleteUserById(Long id) throws GlobalException {
        if (this.userRepository.findById(id).isPresent()) {
            this.userRepository.deleteById(id);
        } else {
            throw new GlobalException("Delete _ there no user with this id: " + id);
        }
    }

    // find user by userName
    public Optional<User> findUserByUsername(String userName) throws GlobalException {
        Optional<User> user = Optional.ofNullable(this.userRepository.findByUserName(userName));
        if (user.isPresent()) {
            return user;
        } else {
            throw new GlobalException("There is no user with this username");
        }
    }
}
