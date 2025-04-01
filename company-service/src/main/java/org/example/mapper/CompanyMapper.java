package org.example.mapper;

import org.example.model.dto.CompanyInputDto;
import org.example.model.dto.CompanyOutputDto;
import org.example.model.domain.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Mapping(target = "employees", ignore = true)
    CompanyOutputDto toDto(Company company);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employeeIds", ignore = true)
    Company toEntity(CompanyInputDto companyDto);

    List<CompanyOutputDto> toListDto(List<Company> companies);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employeeIds", ignore = true)
    void updateCompanyFromDto(CompanyInputDto companyInputDto, @MappingTarget Company company);
}