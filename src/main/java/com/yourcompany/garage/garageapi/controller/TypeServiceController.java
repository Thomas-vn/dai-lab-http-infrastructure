package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.dto.TypeServiceDTOnative;
import com.yourcompany.garage.garageapi.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/type-services")
public class TypeServiceController {

    private final TypeServiceService typeServiceService;

    @Autowired
    public TypeServiceController(TypeServiceService typeServiceService) {
        this.typeServiceService = typeServiceService;
    }

    @GetMapping
    public List<TypeServiceDTOnative> getAllTypeServices() {
        return typeServiceService.getAllTypeServices();
    }

    @GetMapping("/{price}/{secondPrice}")
    public List<TypeServiceDTOnative> getAllTypeServicesByPrice(@PathVariable("price") Double price, @PathVariable("secondPrice") Double secondPrice) {
        return typeServiceService.getAllTypeServicesByPrice(price, secondPrice);
    }

    @PostMapping
    public void createTypeService(@RequestBody TypeServiceDTOnative typeService) {
        typeServiceService.createTypeService(typeService);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeService(@PathVariable("id") Integer typeServiceID) {
        typeServiceService.deleteTypeService(typeServiceID);
    }


}
