package org.example.service;

import org.example.dto.UserInputDto;
import org.example.dto.UserOutputDto;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
public interface UserService {

    UserOutputDto create(UserInputDto userInputDto);

    UserOutputDto update(Long id, UserInputDto userInputDto);

    void delete(Long id);

    UserOutputDto getUserById(Long id);

    List<UserOutputDto> getUsersByLastName(String lastName);

    List<UserOutputDto> getAllUsers();
}