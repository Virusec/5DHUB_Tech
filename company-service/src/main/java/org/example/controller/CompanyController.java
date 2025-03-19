package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CompanyDto;
import org.example.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    Logger logger = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        logger.info("The method was invoked to create company.");
        return companyService.create(companyDto);
    }

    @PutMapping("update")
    public CompanyDto updateCompany(@RequestBody CompanyDto companyDto) {
        logger.info("The method was invoked to update company with id = {}.", companyDto.getId());
        return companyService.update(companyDto);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        logger.info("The method was invoked to delete company with id = {}.",id);
        companyService.delete(id);
    }

    @GetMapping("{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        logger.info("The method was invoked to find company by id = {}.",id);
        return companyService.getCompanyById(id);
    }

    @GetMapping("search")
    public List<CompanyDto> getCompanyByName(@RequestParam String name) {
        logger.info("The method was invoked to find company by name = {}.", name);
        return companyService.getCompanyByName(name);
    }

    @GetMapping("all")
    public List<CompanyDto> getAllCompanies() {
        logger.info("The method was invoked to find all existing companies.");
        return companyService.getAllCompanies();
    }
}