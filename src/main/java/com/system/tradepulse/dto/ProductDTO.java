package com.system.tradepulse.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "SKU is required")
    private String sku;

    @NotBlank(message = "name of the product cannot be blank")
    private String name;

    @Min(value = 1000, message = "base price cannot be less than 1000")
    @Positive(message = "price should be greater than zero")
    private BigDecimal basePriceInr;

    @Min(value = 20, message = "stock quantity cannot be less than 20")
    @Positive
    private Integer stockQuantity;
}
