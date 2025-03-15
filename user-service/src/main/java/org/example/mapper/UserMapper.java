package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Anatoliy Shikin
 */
public class UserMapper {
    public static User toEntity(UserDto userDto) {
        return new User(userDto.getFirstName()
                , userDto.getLastName()
                , userDto.getPhoneNumber());
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getId()
                , user.getFirstName()
                , user.getLastName()
                ,user.getPhoneNumber());
    }

    public static Collection<UserDto> toListDto(Collection<User> users) {
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
