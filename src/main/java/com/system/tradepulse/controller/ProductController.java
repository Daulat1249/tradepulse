package com.system.tradepulse.controller;

import com.system.tradepulse.advices.ApiResponse;
import com.system.tradepulse.dto.ProductDTO;
import com.system.tradepulse.dto.ProductUpdateDTO;
import com.system.tradepulse.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @PutMapping(path = "/products/{id}/price")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@RequestBody @Valid ProductUpdateDTO productDTO, @PathVariable Long id){
        ProductDTO savedProduct = productService.updateProduct(id, productDTO);
        ApiResponse<ProductDTO> apiResponse = new ApiResponse<>(savedProduct);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                        @RequestParam(defaultValue = "10") Integer PAGE_SIZE,
                                                                        @RequestParam(defaultValue = "id") String sortBy){
        Page<ProductDTO> products = productService.getAllProducts(pageNumber, PAGE_SIZE, sortBy);
        return ResponseEntity.ok(new ApiResponse<>(products));
    }
}
