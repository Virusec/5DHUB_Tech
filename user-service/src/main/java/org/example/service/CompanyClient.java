package org.example.service;

import org.example.model.dto.CompanyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Anatoliy Shikin
 */
@FeignClient(name = "company-service", url = "${user.service.url}")
public interface CompanyClient {
    @GetMapping(name = "companies/{id}")
    CompanyDto getCompanyById(@PathVariable Long id);
}
