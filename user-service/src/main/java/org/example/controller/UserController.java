package org.example.controller;

import org.example.dto.UserDto;
import org.example.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Anatoliy Shikin
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userServiceImpl.createUser(userDto);
        return ResponseEntity.ok(createdUser);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userServiceImpl.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/search")
    public ResponseEntity<Collection<UserDto>> getUsersByLastName(@RequestParam String lastName) {
        Collection<UserDto> usersByLastName = userServiceImpl.getUsersByLastName(lastName);
        return ResponseEntity.ok(usersByLastName);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<UserDto>> getAllUsers() {
        Collection<UserDto> allUsers = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}
