package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.dto.ServiceDTO;
import com.yourcompany.garage.garageapi.dto.ServiceDTOnative;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Object[]> rawResults = serviceRepository.getAllServicesCustom();

        return rawResults.stream()
                .map(this::mapRowToServiceDTO)  // Using the new map function
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDTO getServiceById(Integer serviceID) {
        List<Object[]> results = serviceRepository.findByIdCustom(serviceID);

        if (results == null || results.isEmpty()) {
            throw new ResourceNotFoundException("Service not found with id: " + serviceID);
        }

        return mapRowToServiceDTO(results.get(0)); // Get the first row if there's a result
    }

    @Override
    public void createService(ServiceDTOnative service) {
        serviceRepository.saveCustom(
                service.getServiceID(),
                service.getCommentaire(),
                service.getHeuresDeTravail(),
                service.getReparationID(),
                service.getNoAVSMecanicien(),
                service.getTypeserviceid()
        );
    }

    @Override
    public void deleteService(Integer serviceID) {
        serviceRepository.deleteByIdCustom(serviceID);
    }

    /**
     * This method maps a row of raw data from the database into a ServiceDTO object.
     */
    private ServiceDTO mapRowToServiceDTO(Object[] row) {
        return new ServiceDTO(
                (Integer) row[0],                // serviceID
                (String) row[1],                 // commentaire
                (Integer) row[2],                // heuresDeTravail
                (Integer) row[3],                // reparationID
                (Long) row[4],                   // noAVSMecanicien
                (String) row[5]                  // description
        );
    }
}