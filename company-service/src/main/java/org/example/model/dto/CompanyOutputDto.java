package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anatoliy Shikin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyOutputDto {
    private Long id;
    private String name;
    private Double budget;
}
