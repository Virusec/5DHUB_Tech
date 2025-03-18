package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        logger.info("The method was invoked to create user.");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDto));
    }

    @PutMapping("update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        logger.info("The method was invoked to update user with id = {}.", userDto.getId());
        return ResponseEntity.ok(userService.update(userDto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        logger.info("The method was invoked to delete user with id = {}.",id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        logger.info("The method was invoked to find user by id = {}.",id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("search")
    public ResponseEntity<List<UserDto>> getUsersByLastName(@RequestParam String lastName) {
        logger.info("The method was invoked to find users by last name = {}.", lastName);
        List<UserDto> usersByLastName = userService.getUsersByLastName(lastName);
        return ResponseEntity.ok(usersByLastName);
    }

    @GetMapping("all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("The method was invoked to find all existing users.");
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}
