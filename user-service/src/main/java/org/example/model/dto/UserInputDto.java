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
public class UserInputDto {
//    TODO @NotBlank(message = "First name must not be empty")
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
