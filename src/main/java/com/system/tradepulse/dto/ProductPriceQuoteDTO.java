package com.system.tradepulse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceQuoteDTO {
    private Long productId;

    private String productName;

    private String targetCurrency;

    private BigDecimal basePriceInr;

    private BigDecimal exchangeRate;

    private BigDecimal convertedPrice;
}
