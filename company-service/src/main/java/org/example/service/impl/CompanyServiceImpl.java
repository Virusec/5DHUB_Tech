package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CompanyDto;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.CompanyMapper;
import org.example.model.Company;
import org.example.repository.CompanyRepository;
import org.example.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    @Transactional
    @Override
    public CompanyDto create(CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        CompanyDto createdCompany = companyMapper.toDto(companyRepository.save(company));
        log.debug("Company with id = {} has been created.", company.getId());
        return createdCompany;
    }

    @Transactional
    @Override
    public CompanyDto update(CompanyDto companyDto) {
        Company company = getCompanyOrThrow(companyDto.getId());
        company.setName(companyDto.getName());
        company.setBudget(companyDto.getBudget());
        CompanyDto updatedCompany = companyMapper.toDto(companyRepository.save(company));
        log.debug("Company with id = {} has been updated.", company.getId());
        return updatedCompany;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getCompanyOrThrow(id);
        companyRepository.deleteById(id);
        log.debug("Company with id = {} has been deleted.", id);
    }


    @Override
    public CompanyDto getCompanyById(Long id) {
        Company company = getCompanyOrThrow(id);
        CompanyDto foundCompany = companyMapper.toDto(company);
        log.debug("Company with id = {} has been found.", company.getId());
        return foundCompany;
    }

    @Override
    public CompanyDto getCompanyByName(String name) {
        Company company = companyRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Company with name " + name + " was not found!"));
        CompanyDto foundCompanyByName = companyMapper.toDto(company);
        log.debug("Company with name = {} has been found.", name);
        return foundCompanyByName;
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> allCompanies = companyRepository.findAll();
        List<CompanyDto> listDto = companyMapper.toListDto(allCompanies);
        if (listDto.isEmpty()) {
            log.debug("The list does not contain any companies.");
        } else {
            log.debug("Existing companies have been found.");
        }
        return listDto;
    }

    private Company getCompanyOrThrow(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " was not found!"));
    }
}