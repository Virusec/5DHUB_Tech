package org.example.service;

import org.example.dto.CompanyDto;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
public interface CompanyService {

    CompanyDto create(CompanyDto companyDto);

    CompanyDto update(CompanyDto companyDto);

    void delete(Long id);

    CompanyDto getCompanyById(Long id);

    CompanyDto getCompanyByName(String name);

    List<CompanyDto> getAllCompanies();

}
