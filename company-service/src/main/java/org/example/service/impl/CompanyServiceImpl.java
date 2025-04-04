package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.CompanyInputDto;
import org.example.model.dto.CompanyOutputDto;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.CompanyMapper;
import org.example.model.domain.Company;
import org.example.model.dto.UserDto;
import org.example.repository.CompanyRepository;
import org.example.service.CompanyService;
import org.example.service.UserClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final UserClient userClient;

    @Transactional
    @Override
    public CompanyOutputDto create(CompanyInputDto companyInputDto) {
        Company company = companyMapper.toEntity(companyInputDto);
        CompanyOutputDto createdCompany = companyMapper.toDto(companyRepository.save(company));
        log.debug("Company with id = {} has been created.", company.getId());
        return createdCompany;
    }

    @Transactional
    @Override
    public CompanyOutputDto update(Long id, CompanyInputDto companyInputDto) {
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
        log.debug("Company with id = {} has been found.", company.getId());
        return getCompanyUsers(company);
    }

    @Override
    public CompanyOutputDto getCompanyByName(String name) {
        Company company = companyRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Company with name " + name + " was not found!"));
        log.debug("Company with name = {} has been found.", name);
        return getCompanyUsers(company);
    }

    @Override
    public Page<CompanyOutputDto> getAllCompanies(Pageable pageable) {
        Page<Company> companyPage = companyRepository.findAll(pageable);
        if (companyPage.isEmpty()) {
            log.debug("The list does not contain any companies.");
        } else {
            log.debug("Existing companies have been found.");
        }
        return companyPage.map(companyMapper::toDto);
    }

    private Company getCompanyOrThrow(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " was not found!"));
    }

    private CompanyOutputDto getCompanyUsers(Company company) {
        List<UserDto> users = userClient.getUsersByCompanyId(company.getId());
        CompanyOutputDto companyOutputDto = companyMapper.toDto(company);

//                CompanyOutputDto foundCompany = companyMapper.toDto(company);
//        if (company.getEmployeeIds() != null && !company.getEmployeeIds().isEmpty()) {
//            List<UserDto> users = userClient.getUsersByCompanyId(company.getId());
//            foundCompany.setEmployees(users);
//        } else {
//            foundCompany.setEmployees(Collections.emptyList());
//        }
//        companyOutputDto.setEmployeesId(company.getEmployeeIds());

        companyOutputDto.setEmployees(users);
        return companyOutputDto;
    }
}