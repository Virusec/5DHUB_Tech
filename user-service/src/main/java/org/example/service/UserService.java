package org.example.service;

import org.example.dto.UserDto;

import java.util.Collection;

/**
 * @author Anatoliy Shikin
 */
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    Collection<UserDto> getUsersByLastName(String lastName);

    Collection<UserDto> getAllUsers();
}
