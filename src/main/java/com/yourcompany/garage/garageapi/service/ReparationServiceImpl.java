package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.dto.ReparationDTO;
import com.yourcompany.garage.garageapi.dto.ReparationDTOnative;
import com.yourcompany.garage.garageapi.entity.Reparation;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReparationServiceImpl implements ReparationService {

    private final ReparationRepository reparationRepository;

    @Autowired
    public ReparationServiceImpl(ReparationRepository reparationRepository) {
        this.reparationRepository = reparationRepository;
    }

    // CRUD Operations

    @Override
    public List<ReparationDTO> getAllReparations() {
        return mapRowsToReparationDTO(reparationRepository.findAllCustom());
    }

    @Override
    public ReparationDTO getReparationById(Integer reparationID) {
        List<Object[]> results = reparationRepository.findByIdCustom(reparationID);
        if (results == null || results.isEmpty()) {
            throw new ResourceNotFoundException("Reparation not found with id: " + reparationID);
        }
        return mapRowToReparationDTO(results.get(0));
    }

    @Override
    public List<ReparationDTO> getReparationByNumeroChassis(String numeroChassis) {
        return mapRowsToReparationDTO(reparationRepository.findByNumeroChassisCustom(numeroChassis));
    }

    @Override
    public Reparation createReparation(ReparationDTOnative reparation) {
        return reparationRepository.saveCustom(
                reparation.getReparationID(),
                reparation.getPrix(),
                reparation.getDateDebut(),
                reparation.getDateFin(),
                reparation.getNumeroChassis(),
                reparation.getVille());
    }

    @Override
    public void deleteReparation(Integer reparationID) {
        // Fetch the reparation details from the repository
        List<Object[]> results = reparationRepository.findByIdCustom(reparationID);
        if (results == null || results.isEmpty()) {
            throw new ResourceNotFoundException("Reparation not found with id: " + reparationID);
        }
        // Delete the reparation by its ID
        reparationRepository.deleteCustom(reparationID);
    }

    // Private helper methods

    /**
     * Maps a list of raw database rows to a list of ReparationDTO objects.
     */
    private List<ReparationDTO> mapRowsToReparationDTO(List<Object[]> rows) {
        return rows.stream()
                .map(this::mapRowToReparationDTO)
                .collect(Collectors.toList());
    }

    /**
     * Maps a single row of raw data from the database to a ReparationDTO object.
     */
    private ReparationDTO mapRowToReparationDTO(Object[] row) {
        return new ReparationDTO(
                (Integer) row[0],                               // reparationid
                (BigDecimal) row[1],                            // prix
                convertToLocalDate(row[2]),                     // datedebut
                row[3] != null ? convertToLocalDate(row[3]) : null, // datefin
                (String) row[4],                                // numerochassis
                (String) row[5]                                 // ville
        );
    }

    private LocalDate convertToLocalDate(Object sqlDate) {
        if (sqlDate instanceof java.sql.Date) {
            return ((java.sql.Date) sqlDate).toLocalDate();
        }
        return null; // Handle null values gracefully
    }
}