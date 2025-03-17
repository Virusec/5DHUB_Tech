package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userDto.getId() + " not found"));
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        User updateUser = userRepository.save(user);
        return UserMapper.toDto(updateUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id = " + id + " not found!"));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUsersByLastName(String lastName) {
        List<User> byLastNameIgnoreCase = userRepository.findByLastNameIgnoreCase(lastName);
        if (byLastNameIgnoreCase.isEmpty()) {
            throw new IllegalArgumentException("User with lastName = " + lastName + " not found!");
        }
        return UserMapper.toListDto(byLastNameIgnoreCase);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return UserMapper.toListDto(allUsers);
    }
}