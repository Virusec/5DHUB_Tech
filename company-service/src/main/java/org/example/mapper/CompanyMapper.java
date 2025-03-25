package org.example.mapper;

import org.example.dto.CompanyDto;
import org.example.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto toDto(Company company);

    Company toEntity(CompanyDto companyDto);

    List<CompanyDto> toListDto(List<Company> companies);
}