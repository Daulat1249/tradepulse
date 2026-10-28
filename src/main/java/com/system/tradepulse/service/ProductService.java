package com.system.tradepulse.service;

import com.system.tradepulse.dto.ProductDTO;
import com.system.tradepulse.dto.ProductUpdateDTO;
import com.system.tradepulse.dto.SupplierDTO;
import com.system.tradepulse.entities.ProductEntity;
import com.system.tradepulse.entities.SupplierEntity;
import com.system.tradepulse.exceptions.ResourceNotFoundException;
import com.system.tradepulse.repositories.ProductRepository;
import com.system.tradepulse.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProductDTO updateProduct(Long productId, ProductUpdateDTO productUpdateDTO){
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with id:"+ productId));
        productEntity.setBasePriceInr(productUpdateDTO.getBasePriceInr());
        productEntity.setStockQuantity(productUpdateDTO.getStockQuantity());
        ProductEntity savedProduct = productRepository.save(productEntity);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    public Page<ProductDTO> getAllProducts(Integer pageNumber, Integer PAGE_SIZE, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy));
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        return productEntities.map(productEntity -> modelMapper.map(productEntity, ProductDTO.class));

        /*return productEntities.getContent()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .toList();  //return type in this case will be List<ProductDTO>
                */
    }

}
