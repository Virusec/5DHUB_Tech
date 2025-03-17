package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
public class UserMapper {
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }

    public static List<UserDto> toListDto(List<User> allUsers) {
        return allUsers.stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
