package com.system.tradepulse.controller;

import com.system.tradepulse.advices.ApiResponse;
import com.system.tradepulse.dto.ProductDTO;
import com.system.tradepulse.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/suppliers/{supplierId}/products")
    public ResponseEntity<ApiResponse<ProductDTO>> createProductById(@PathVariable(name = "supplierId") Long supplierId, @RequestBody @Valid ProductDTO productDTO) {
        ProductDTO savedProduct = productService.createProductById(productDTO, supplierId);
        ApiResponse<ProductDTO> apiResponse = new ApiResponse<>(savedProduct);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

}
