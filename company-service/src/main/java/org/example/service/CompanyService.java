package org.example.service;

import org.example.dto.CompanyInputDto;
import org.example.dto.CompanyOutputDto;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
public interface CompanyService {

    CompanyOutputDto create(CompanyInputDto companyInputDto);

    CompanyOutputDto update(Long id, CompanyInputDto companyInputDto);

    void delete(Long id);

    CompanyOutputDto getCompanyById(Long id);

    CompanyOutputDto getCompanyByName(String name);

    List<CompanyOutputDto> getAllCompanies();

}