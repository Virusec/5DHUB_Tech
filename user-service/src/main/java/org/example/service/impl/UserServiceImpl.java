package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.exceptions.EntityNotFoundException;
import org.example.mapper.UserMapper;
import org.example.model.User;
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
    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        UserDto createdUser = userMapper.toDto(userRepository.save(user));
        log.debug("User with id = {} has been created.", user.getId());
        return createdUser;
    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto) {
        User user = getUserOrThrow(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        UserDto updatedUser = userMapper.toDto(userRepository.save(user));
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
    public UserDto getUserById(Long id) {
        User user = getUserOrThrow(id);
        UserDto foundUser = userMapper.toDto(user);
        log.debug("User with id = {} has been found.", user.getId());
        return foundUser;
    }

    @Override
    public List<UserDto> getUsersByLastName(String lastName) {
        List<User> byLastNameIgnoreCase = userRepository.findByLastNameIgnoreCase(lastName);
        if (byLastNameIgnoreCase.isEmpty()) {
            throw new EntityNotFoundException("User with last name = " + lastName + " was not found!");
        }
        List<UserDto> usersList = userMapper.toListDto(byLastNameIgnoreCase);
        log.debug("Users with last name = {} have been found.", lastName);
        return usersList;
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> usersList = userMapper.toListDto(allUsers);
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
}