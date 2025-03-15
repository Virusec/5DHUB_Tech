package org.example.service.impl;

import org.example.dto.CompanyDto;
import org.example.mapper.CompanyMapper;
import org.example.model.Company;
import org.example.repository.CompanyRepository;
import org.example.service.CompanyService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.toEntity(companyDto);
        return CompanyMapper.toDto(companyRepository.save(company));
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company with id = " + id + " not found!"));
        //Временно, чтобы подтягивал ids
//        Hibernate.initialize(company.getEmployeeIds());

        return CompanyMapper.toDto(company);
    }

    @Override
    public CompanyDto getCompanyByName(String name) {
        Company company = companyRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalArgumentException("Company with id = " + name + " not found!"));
        return CompanyMapper.toDto(company);
    }

    @Override
    public Collection<CompanyDto> getAllCompanies() {
        List<Company> allCompanies = companyRepository.findAll();
        return CompanyMapper.toListDto(allCompanies);
    }
}