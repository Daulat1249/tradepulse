package com.system.tradepulse.service;

import com.system.tradepulse.client.impl.CurrencyExchangeClientImpl;
import com.system.tradepulse.dto.*;
import com.system.tradepulse.entities.ProductEntity;
import com.system.tradepulse.entities.SupplierEntity;
import com.system.tradepulse.exceptions.ResourceNotFoundException;
import com.system.tradepulse.repositories.ProductRepository;
import com.system.tradepulse.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final CurrencyExchangeClientImpl currencyExchangeClientImpl;


    Logger log = LoggerFactory.getLogger(CurrencyExchangeClientImpl.class);
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
        log.info("Price is updated for productId: {}, productUpateDTO.getBasePriceInr(): {}", productId, savedProduct.getBasePriceInr());
        log.info("Stock Quantity Updated productEntity.setStockQuantity: {}", savedProduct.getStockQuantity());
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

    public ProductPriceQuoteDTO getProductPriceQuote(Long productId, String currency){

        log.debug("Starting price quote calculation for productId: {}",productId);

        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product Not found with id: "+productId));
        String currencyUpper = currency.toUpperCase();
        CurrencyExchangeDTO currencyExchangeDTO = currencyExchangeClientImpl.getCurrentExchange(currencyUpper);
        Map<String, BigDecimal> tempRate= currencyExchangeDTO.getRates();
        BigDecimal currentRate = tempRate.get(currencyUpper);
        BigDecimal convertedPrice = productEntity.getBasePriceInr().multiply(currentRate);

        log.debug("Currency converted by getProductPriceQuote");
        ProductPriceQuoteDTO productPriceQuoteDTO = new ProductPriceQuoteDTO();
        productPriceQuoteDTO.setConvertedPrice(convertedPrice);
        productPriceQuoteDTO.setProductId(productId);
        productPriceQuoteDTO.setExchangeRate(currentRate);
        productPriceQuoteDTO.setProductName(productEntity.getName());
        productPriceQuoteDTO.setTargetCurrency(currencyUpper);
        productPriceQuoteDTO.setBasePriceInr(productEntity.getBasePriceInr());

        return productPriceQuoteDTO;
    }

}
