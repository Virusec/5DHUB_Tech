package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.UserInputDto;
import org.example.model.dto.UserOutputDto;
import org.example.exceptions.EntityNotFoundException;
import org.example.mapper.UserMapper;
import org.example.model.domain.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserOutputDto create(UserInputDto userInputDto) {
        validateUserInput(userInputDto);
        User user = userMapper.toEntity(userInputDto);
        UserOutputDto createdUser = userMapper.toDto(userRepository.save(user));
        log.debug("User with id = {} has been created.", user.getId());
        return createdUser;
    }

    @Transactional
    @Override
    public UserOutputDto update(Long id, UserInputDto userInputDto) {
        validateUserInput(userInputDto);
        User user = getUserOrThrow(id);
        userMapper.updateUserFromDto(userInputDto, user);
        UserOutputDto updatedUser = userMapper.toDto(userRepository.save(user));
        log.debug("User with id = {} has been updated.", user.getId());
        return updatedUser;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getUserOrThrow(id);
        userRepository.deleteById(id);
        log.debug("User with id = {} has been deleted.", id);
    }

    @Override
    public UserOutputDto getUserById(Long id) {
        User user = getUserOrThrow(id);
        UserOutputDto foundUser = userMapper.toDto(user);
        log.debug("User with id = {} has been found.", user.getId());
        return foundUser;
    }

    @Override
    public List<UserOutputDto> getUsersByLastName(String lastName) {
        List<User> users = userRepository.findByLastNameIgnoreCase(lastName);
        if (users.isEmpty()) {
            throw new EntityNotFoundException("User with last name = " + lastName + " was not found!");
        }
        List<UserOutputDto> usersList = userMapper.toListDto(users);
        log.debug("Users with last name = {} have been found.", lastName);
        return usersList;
    }

    @Override
    public List<UserOutputDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserOutputDto> usersList = userMapper.toListDto(allUsers);
        if (usersList.isEmpty()) {
            log.debug("The list does not contain any users!");
        } else {
            log.debug("Existing users have been found.");
        }
        return usersList;
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id = " + id + " was not found!"));
    }

    private void validateUserInput(UserInputDto userInputDto) {
        if (userInputDto.getFirstName() == null || userInputDto.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name must not be empty");
        }
        if (userInputDto.getLastName() == null || userInputDto.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name must not be empty");
        }
        if (userInputDto.getPhoneNumber() == null || userInputDto.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number must not be empty");
        }
    }
}