package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.dto.ReparationDTO;
import com.yourcompany.garage.garageapi.dto.ReparationDTOnative;
import com.yourcompany.garage.garageapi.entity.Reparation;

import java.util.List;

public interface ReparationService {

    // CRUD Operations
    List<ReparationDTO> getAllReparations();
    List<ReparationDTO> getReparationByNumeroChassis(String numeroChassis);

    ReparationDTO getReparationById(Integer reparationID);
    Reparation createReparation(ReparationDTOnative reparation);
    void deleteReparation(Integer reparationID);

}