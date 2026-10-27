package com.system.tradepulse.controller;

import com.system.tradepulse.advices.ApiResponse;
import com.system.tradepulse.dto.SupplierDTO;
import com.system.tradepulse.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<SupplierDTO>> createNewSupplier(@RequestBody @Valid SupplierDTO supplierDTO){
        SupplierDTO savedDTO = supplierService.createNewSupplier(supplierDTO);
        ApiResponse<SupplierDTO> apiResponse = new ApiResponse<>(savedDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
