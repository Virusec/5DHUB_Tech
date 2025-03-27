package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.CompanyInputDto;
import org.example.model.dto.CompanyOutputDto;
import org.example.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

/**
 * @author Anatoliy Shikin
 */
@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyOutputDto createCompany(@RequestBody @Valid CompanyInputDto companyInputDto) {
        log.info("The method was invoked to create company.");
        return companyService.create(companyInputDto);
    }

    @PutMapping("update/{id}")
    public CompanyOutputDto updateCompany(@PathVariable Long id,
                                          @RequestBody @Valid CompanyInputDto companyInputDto) {
        log.info("The method was invoked to update company with id = {}.", id);
        return companyService.update(id, companyInputDto);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        log.info("The method was invoked to delete company with id = {}.", id);
        companyService.delete(id);
    }

    @GetMapping("{id}")
    public CompanyOutputDto getCompanyById(@PathVariable Long id) {
        log.info("The method was invoked to find company by id = {}.", id);
        return companyService.getCompanyById(id);
    }

    @GetMapping("search")
    public CompanyOutputDto getCompanyByName(@RequestParam String name) {
        log.info("The method was invoked to find company by name = {}.", name);
        return companyService.getCompanyByName(name);
    }

    @GetMapping("all")
    public Page<CompanyOutputDto> getAllCompanies(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        log.info("The method was invoked to find all existing companies.");
        return companyService.getAllCompanies(pageable);
    }
}