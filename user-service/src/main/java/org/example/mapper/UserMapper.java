package org.example.mapper;

import org.example.model.dto.UserInputDto;
import org.example.model.dto.UserOutputDto;
import org.example.model.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "companyDto", ignore = true)
    UserOutputDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    User toEntity(UserInputDto userInputDto);

    List<UserOutputDto> toListDto(List<User> allUsers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    void updateUserFromDto(UserInputDto userInputDto, @MappingTarget User user);
}