package com.system.tradepulse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class ProductEntity extends AuditableEntity{

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

    @ManyToOne  //many to one is the owning side
    @JoinColumn(nullable = false)
    @NotAudited
    private SupplierEntity supplierEntity;
}
