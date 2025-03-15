package org.example.service.impl;

import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id = " + id + " not found!"));
        return UserMapper.toDto(user);
    }

    @Override
    public Collection<UserDto> getUsersByLastName(String lastName) {
        List<User> byLastNameIgnoreCase = userRepository.findByLastNameIgnoreCase(lastName);
        if (byLastNameIgnoreCase.isEmpty()) {
            throw new IllegalArgumentException("User with lastName = " + lastName + " not found!");
        }
        return UserMapper.toListDto(byLastNameIgnoreCase);
    }

    @Override
    public Collection<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return UserMapper.toListDto(allUsers);
    }
}