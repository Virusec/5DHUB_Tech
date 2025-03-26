package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.UserInputDto;
import org.example.model.dto.UserOutputDto;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutputDto createUser(@Validated @RequestBody UserInputDto userInputDto) {
        log.info("The method was invoked to create user.");
        return userService.create(userInputDto);
    }

    @PutMapping("update/{id}")
    public UserOutputDto updateUser(@PathVariable Long id,
                              @RequestBody UserInputDto userInputDto) {
        log.info("The method was invoked to update user with id = {}.", id);
        return userService.update(id, userInputDto);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        log.info("The method was invoked to delete user with id = {}.", id);
        userService.delete(id);
    }

    @GetMapping("{id}")
    public UserOutputDto getUserById(@PathVariable Long id) {
        log.info("The method was invoked to find user by id = {}.", id);
        return userService.getUserById(id);
    }

    @GetMapping("search")
    public List<UserOutputDto> getUsersByLastName(@RequestParam String lastName) {
        log.info("The method was invoked to find users by last name = {}.", lastName);
        return userService.getUsersByLastName(lastName);
    }

    @GetMapping("all")
    public List<UserOutputDto> getAllUsers() {
        log.info("The method was invoked to find all existing users.");
        return userService.getAllUsers();
    }
}