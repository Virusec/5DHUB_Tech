package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anatoliy Shikin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {
    private Long id;
    private String name;
    private Double budget;
//TODO    private List<Long> employeesIds;
}
