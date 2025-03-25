package org.example.mapper;

import org.example.dto.UserInputDto;
import org.example.dto.UserOutputDto;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserOutputDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserInputDto userInputDto);

    List<UserOutputDto> toListDto(List<User> allUsers);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserInputDto userInputDto, @MappingTarget User user);
}