package org.example.mapper;

import org.example.dto.CompanyInputDto;
import org.example.dto.CompanyOutputDto;
import org.example.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyOutputDto toDto(Company company);

    @Mapping(target = "id", ignore = true)
    Company toEntity(CompanyInputDto companyDto);

    List<CompanyOutputDto> toListDto(List<Company> companies);

    @Mapping(target = "id", ignore = true)
    void updateCompanyFromDto(CompanyInputDto companyInputDto, @MappingTarget Company company);
}