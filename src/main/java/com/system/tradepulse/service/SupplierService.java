package com.system.tradepulse.service;

import com.system.tradepulse.dto.SupplierDTO;
import com.system.tradepulse.entities.SupplierEntity;
import com.system.tradepulse.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierDTO createNewSupplier(SupplierDTO supplierDTO){
        SupplierEntity supplierEntity = modelMapper.map(supplierDTO,SupplierEntity.class);
        SupplierEntity savedEntity = supplierRepository.save(supplierEntity);
        return modelMapper.map(savedEntity, SupplierDTO.class);
    }
}
