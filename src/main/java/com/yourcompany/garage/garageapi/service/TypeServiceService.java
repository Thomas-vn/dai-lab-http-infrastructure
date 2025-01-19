package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.dto.TypeServiceDTOnative;

import java.util.List;

public interface TypeServiceService {

    List<TypeServiceDTOnative> getAllTypeServices();
    void createTypeService(TypeServiceDTOnative typeService);
    void deleteTypeService(Integer typeServiceID);
    List<TypeServiceDTOnative> getAllTypeServicesByPrice(Double price, Double secondPrice);

}
