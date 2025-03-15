package org.example.mapper;

import org.example.dto.CompanyDto;
import org.example.model.Company;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Anatoliy Shikin
 */
public class CompanyMapper {
    public static Company toEntity(CompanyDto companyDto) {
        return new Company(companyDto.getName()
                , companyDto.getBudget()
                , companyDto.getEmployeesIds());
    }

    public static CompanyDto toDto(Company company) {
        return new CompanyDto(company.getId()
                , company.getName()
                , company.getBudget()
                , company.getEmployeeIds());
    }

    public static Collection<CompanyDto> toListDto(Collection<Company> companies) {
        return companies.stream()
                .map(CompanyMapper::toDto)
                .collect(Collectors.toList());
    }
}
