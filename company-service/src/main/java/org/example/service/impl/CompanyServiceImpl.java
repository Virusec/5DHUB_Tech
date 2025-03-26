package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.CompanyInputDto;
import org.example.model.dto.CompanyOutputDto;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.CompanyMapper;
import org.example.model.domain.Company;
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
    public CompanyOutputDto create(CompanyInputDto companyInputDto) {
        validateCompanyInput(companyInputDto);
        Company company = companyMapper.toEntity(companyInputDto);
        CompanyOutputDto createdCompany = companyMapper.toDto(companyRepository.save(company));
        log.debug("Company with id = {} has been created.", company.getId());
        return createdCompany;
    }

    @Transactional
    @Override
    public CompanyOutputDto update(Long id, CompanyInputDto companyInputDto) {
        validateCompanyInput(companyInputDto);
        Company company = getCompanyOrThrow(id);
        companyMapper.updateCompanyFromDto(companyInputDto, company);
        CompanyOutputDto updatedCompany = companyMapper.toDto(companyRepository.save(company));
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
    public CompanyOutputDto getCompanyById(Long id) {
        Company company = getCompanyOrThrow(id);
        CompanyOutputDto foundCompany = companyMapper.toDto(company);
        log.debug("Company with id = {} has been found.", company.getId());
        return foundCompany;
    }

    @Override
    public CompanyOutputDto getCompanyByName(String name) {
        Company company = companyRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Company with name " + name + " was not found!"));
        CompanyOutputDto foundCompanyByName = companyMapper.toDto(company);
        log.debug("Company with name = {} has been found.", name);
        return foundCompanyByName;
    }

    @Override
    public List<CompanyOutputDto> getAllCompanies() {
        List<Company> allCompanies = companyRepository.findAll();
        List<CompanyOutputDto> listDto = companyMapper.toListDto(allCompanies);
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

    private void validateCompanyInput(CompanyInputDto companyInputDto) {
        if (companyInputDto.getName() == null || companyInputDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name of company must not be empty");
        }
    }
}