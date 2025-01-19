package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.dto.IdDTO;
import com.yourcompany.garage.garageapi.dto.ServiceDTO;
import com.yourcompany.garage.garageapi.dto.ServiceDTOnative;
import com.yourcompany.garage.garageapi.service.ServiceService;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceDTO> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable("id") int serviceID)
            throws ResourceNotFoundException {
        ServiceDTO service = serviceService.getServiceById(serviceID);
        return ResponseEntity.ok().body(service);
    }

    @PostMapping
    public void createService(@RequestBody ServiceDTOnative service) {
        serviceService.createService(service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable("id") int serviceID) {
        serviceService.deleteService(serviceID);
    }


}
