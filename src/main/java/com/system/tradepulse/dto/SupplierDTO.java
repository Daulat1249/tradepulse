package com.system.tradepulse.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {


    private Long id;

    @NotBlank(message = "Name of the company cannot be blank")
    private String companyName;

    @Email(message = "email should be in the valid format")
    private String contactEmail;

    @NotBlank(message = "Name of the country cannot be blank")
    private String country;
}
