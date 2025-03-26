package org.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anatoliy Shikin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyInputDto {
    @NotBlank(message = "Name of company must not be empty!")
    private String name;
    @NotNull(message = "Budget must not be null")
    @PositiveOrZero(message = "Budget must not be less than 0!")
    private Double budget;
}
