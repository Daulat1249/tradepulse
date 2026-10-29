package com.system.tradepulse.controller;

import com.system.tradepulse.entities.ProductEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuditController {

    private final EntityManager entityManager;

    @GetMapping("/audit/products/{id}/history`")
    public List<ProductEntity> getPostRevision(@PathVariable Long productId) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<Number> revisions = reader.getRevisions(ProductEntity.class, productId);
        return revisions.stream().map(revisionNumber -> reader.find(ProductEntity.class,productId,revisionNumber)).collect(Collectors.toList());
    }
}
