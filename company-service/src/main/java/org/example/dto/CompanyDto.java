package org.example.dto;

import java.util.List;

/**
 * @author Anatoliy Shikin
 */
public class CompanyDto {
    private Long id;
    private String name;
    private Double budget;
    private List<Long> employeesIds;

    public CompanyDto() {
    }

    public CompanyDto(Long id, String name, Double budget, List<Long> employeesIds) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.employeesIds = employeesIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<Long> getEmployeesIds() {
        return employeesIds;
    }

    public void setEmployeesIds(List<Long> employeesIds) {
        this.employeesIds = employeesIds;
    }
}
