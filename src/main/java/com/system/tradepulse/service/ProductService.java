package com.system.tradepulse.service;

import com.system.tradepulse.dto.ProductDTO;
import com.system.tradepulse.dto.SupplierDTO;
import com.system.tradepulse.entities.ProductEntity;
import com.system.tradepulse.entities.SupplierEntity;
import com.system.tradepulse.exceptions.ResourceNotFoundException;
import com.system.tradepulse.repositories.ProductRepository;
import com.system.tradepulse.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public ProductDTO createProductById(ProductDTO productDTO, Long supplierId){
        SupplierEntity supplierEntity = supplierRepository.findById(supplierId).orElseThrow(() -> new ResourceNotFoundException("Supplier Not Found with id: "+supplierId));
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        productEntity.setSupplierEntity(supplierEntity);
        ProductEntity savedEntity = productRepository.save(productEntity);
        return modelMapper.map(savedEntity, ProductDTO.class);
    }
}
