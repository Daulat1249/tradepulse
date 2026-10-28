package com.system.tradepulse.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class ProductUpdateDTO {

    @Min(value = 0, message = "base price cannot be less than 0")
    @Positive(message = "price should be greater than zero")
    @NotNull(message = "Price cannot be blank")
    private BigDecimal basePriceInr;

    @Min(value = 0, message = "stock quantity cannot be less than 0")
    @NotNull(message = "Quantity cannot be null")
    private Integer stockQuantity;
}
