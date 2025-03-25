package org.example.mapper;

import org.example.dto.UserDto;
import org.example.model.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toListDto(List<User> allUsers);
}