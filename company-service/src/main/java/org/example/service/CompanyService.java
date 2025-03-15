package org.example.service;

import org.example.dto.CompanyDto;
import org.example.model.Company;

import java.util.Collection;

/**
 * @author Anatoliy Shikin
 */
public interface CompanyService {
    CompanyDto createCompany(CompanyDto companyDto);

    CompanyDto getCompanyById(Long id);

    CompanyDto getCompanyByName(String name);

    Collection<CompanyDto> getAllCompanies();

}
