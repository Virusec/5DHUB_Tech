package org.example.repository;

import org.example.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByNameIgnoreCase(String name);
}