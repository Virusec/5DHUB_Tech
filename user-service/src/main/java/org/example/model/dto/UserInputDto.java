package org.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anatoliy Shikin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInputDto {
    @NotBlank(message = "First name must not be empty")
    private String firstName;
    @NotBlank(message = "Last name must not be empty")
    private String lastName;
    @NotBlank(message = "Phone Number must not be empty")
    private String phoneNumber;
    @NotNull(message = "Company id must not be empty")
    @Positive(message = "Company id must be a strictly positive number (i.e. 0 is considered as an invalid value).")
    private Long companyId;
}
