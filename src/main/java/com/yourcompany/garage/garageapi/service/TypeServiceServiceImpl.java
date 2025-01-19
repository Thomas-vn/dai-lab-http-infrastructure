package com.yourcompany.garage.garageapi.service;

import java.util.List;
import java.util.stream.Collectors;

import com.yourcompany.garage.garageapi.dto.TypeServiceDTOnative;
import com.yourcompany.garage.garageapi.repository.TypeServiceRepository;

import com.yourcompany.garage.garageapi.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TypeServiceServiceImpl implements TypeServiceService {

    private final TypeServiceRepository typeServiceRepository;

    @Autowired
    public TypeServiceServiceImpl(TypeServiceRepository typeServiceRepository) {
        this.typeServiceRepository = typeServiceRepository;
    }

    @Override
    public List<TypeServiceDTOnative> getAllTypeServices() {
        List<Object[]> results = typeServiceRepository.getAllTypeServicesCustom();
        return results.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TypeServiceDTOnative> getAllTypeServicesByPrice(Double price, Double secondPrice) {
        List<Object[]> results = typeServiceRepository.getAllTypeServicesByPriceCustom(price, secondPrice);
        return results.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void createTypeService(TypeServiceDTOnative typeService) {
        typeServiceRepository.createTypeServiceCustom(typeService.getPrix(), typeService.getDescription());
    }

    @Override
    public void deleteTypeService(Integer typeServiceID) {
        typeServiceRepository.deleteByIdCustom(typeServiceID);
    }

    private TypeServiceDTOnative convertToDTO(Object[] result) {
        Integer typeServiceId = ((Number) result[0]).intValue();
        Double prix = ((Number) result[1]).doubleValue();
        String description = (String) result[2];
        return new TypeServiceDTOnative(typeServiceId, prix, description);
    }
}