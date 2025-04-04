package org.example.service;

import org.example.model.dto.UserInputDto;
import org.example.model.dto.UserOutputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
public interface UserService {

    UserOutputDto create(UserInputDto userInputDto);

    UserOutputDto update(Long id, UserInputDto userInputDto);

    void delete(Long id);

    UserOutputDto getUserById(Long id);

    List<UserOutputDto> getUsersByCompanyId(Long id);

    Page<UserOutputDto> getUsersByLastName(Pageable pageable, String lastName);

    Page<UserOutputDto> getAllUsers(Pageable pageable);
}