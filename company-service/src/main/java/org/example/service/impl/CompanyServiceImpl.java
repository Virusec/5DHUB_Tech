package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.CompanyDto;
import org.example.exception.CompanyNotFoundException;
import org.example.mapper.CompanyMapper;
import org.example.model.Company;
import org.example.repository.CompanyRepository;
import org.example.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        CompanyDto createdCompany = companyMapper.toDto(companyRepository.save(company));
        logger.debug("Company with id = {} has been created.", company.getId());
        return createdCompany;
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        Company company = companyRepository.findById(companyDto.getId())
                .orElseThrow(() -> new CompanyNotFoundException(companyDto.getId()));
        company.setId(companyDto.getId());
        company.setName(companyDto.getName());
        company.setBudget(companyDto.getBudget());
        CompanyDto updatedUser = companyMapper.toDto(companyRepository.save(company));
        logger.debug("Company with id = {} has been updated.", company.getId());
        return updatedUser;
    }

    @Override
    public void delete(Long id) {
        companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        companyRepository.deleteById(id);
        logger.debug("Company with id = {} has been deleted.", id);
    }


    @Override
    public CompanyDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        CompanyDto foundCompany = companyMapper.toDto(company);
        logger.debug("Company with id = {} has been found.", company.getId());
        return foundCompany;
    }

    @Override
    public List<CompanyDto> getCompanyByName(String name) {
        List<Company> companyList = companyRepository.findByNameIgnoreCase(name);
        if (companyList.isEmpty()) {
            throw new CompanyNotFoundException(name);
        }
        List<CompanyDto> foundCompanyByName = companyMapper.toListDto(companyList);
        logger.debug("Company with name = {} has been found.", name);
        return foundCompanyByName;
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> allCompanies = companyRepository.findAll();
        List<CompanyDto> listDto = companyMapper.toListDto(allCompanies);
        if (listDto.isEmpty()) {
            logger.debug("The list does not contain any companies.");
        } else {
            logger.debug("Existing companies have been found.");
        }
        return listDto;
    }
}