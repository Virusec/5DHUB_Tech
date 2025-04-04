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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        User user = userMapper.toEntity(userInputDto);
        UserOutputDto createdUser = userMapper.toDto(userRepository.save(user));
        log.debug("User with id = {} has been created.", user.getId());
        return createdUser;
    }

    @Transactional
    @Override
    public UserOutputDto update(Long id, UserInputDto userInputDto) {
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

//TODO write exception
//        if (user.getCompanyId() != null) {
//            CompanyDto companyDto = companyClient.getCompanyById(user.getCompanyId());
//            foundUser.setCompanyDto(companyDto);
//        }

        log.debug("User with id = {} has been found.", user.getId());
        return foundUser;
    }

    @Override
    public List<UserOutputDto> getUsersByCompanyId(Long id) {
        List<User> usersPage = userRepository.findByCompanyId(id);
        if (usersPage.isEmpty()) {
            throw new EntityNotFoundException("Users with company id = " + id + " was not found!");
        }
        log.debug("Company with id = {} has been found.", id);
        return userMapper.toListDto(usersPage);
    }

    @Override
    public Page<UserOutputDto> getUsersByLastName(Pageable pageable, String lastName) {
        Page<User> usersPage = userRepository.findByLastNameIgnoreCase(pageable, lastName);
        if (usersPage.isEmpty()) {
            throw new EntityNotFoundException("User with last name = " + lastName + " was not found!");
        }
        log.debug("Users with last name = {} have been found.", lastName);
        return usersPage.map(userMapper::toDto);
    }

    @Override
    public Page<UserOutputDto> getAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        if (usersPage.isEmpty()) {
            log.debug("The list does not contain any users!");
        } else {
            log.debug("Existing users have been found.");
        }
        return usersPage.map(userMapper::toDto);
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id = " + id + " was not found!"));
    }
}