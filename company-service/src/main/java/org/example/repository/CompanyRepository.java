package org.example.repository;

import org.example.model.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Anatoliy Shikin
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByNameIgnoreCase(String name);
}