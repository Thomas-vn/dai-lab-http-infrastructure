package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.dto.TypeServiceDTOnative;
import com.yourcompany.garage.garageapi.service.TypeServiceService;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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


}
