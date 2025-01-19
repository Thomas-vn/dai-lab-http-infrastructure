package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.dto.ReparationDTO;
import com.yourcompany.garage.garageapi.dto.ReparationDTOSecond;
import com.yourcompany.garage.garageapi.entity.Reparation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReparationService {

    // CRUD Operations
    List<ReparationDTO> getAllReparations();
    List<ReparationDTO> getReparationByNumeroChassis(String numeroChassis);

    ReparationDTO getReparationById(Integer reparationID);
    Reparation createReparation(ReparationDTOSecond reparation);
    void deleteReparation(Integer reparationID);

}