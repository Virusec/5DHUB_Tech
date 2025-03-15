package org.example.controller;

import org.example.dto.CompanyDto;
import org.example.service.impl.CompanyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Anatoliy Shikin
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto createCompany = companyServiceImpl.createCompany(companyDto);
        return ResponseEntity.ok(createCompany);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        CompanyDto companyDto = companyServiceImpl.getCompanyById(id);
        return ResponseEntity.ok(companyDto);
    }

    @GetMapping("/search")
    public ResponseEntity<CompanyDto> getCompanyByName(@RequestParam String name) {
        CompanyDto companyByName = companyServiceImpl.getCompanyByName(name);
        return ResponseEntity.ok(companyByName);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<CompanyDto>> getAllCompanies() {
        Collection<CompanyDto> allCompanies = companyServiceImpl.getAllCompanies();
        return ResponseEntity.ok(allCompanies);
    }
}