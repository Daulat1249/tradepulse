package com.system.tradepulse.controller;

import com.system.tradepulse.dto.SupplierDTO;
import com.system.tradepulse.service.SupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/suppliers")
    public SupplierDTO createNewSupplier(@RequestBody SupplierDTO supplierDTO){
        return supplierService.createNewSupplier(supplierDTO);
    }
}
