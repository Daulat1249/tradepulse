package com.system.tradepulse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sku;

    @Column
    private String name;

    @Column
    private BigDecimal basePriceInr;

    @Column
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Supplier supplier;
}
