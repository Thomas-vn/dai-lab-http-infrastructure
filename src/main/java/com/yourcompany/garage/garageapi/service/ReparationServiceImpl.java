package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.dto.ReparationDTO;
import com.yourcompany.garage.garageapi.dto.ReparationDTOSecond;
import com.yourcompany.garage.garageapi.entity.Reparation;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        List<Object[]> rawResults = reparationRepository.findAllCustom();
        return rawResults.stream()
                .map(row -> new ReparationDTO(
                        (Integer) row[0],                           // reparationid
                        (BigDecimal) row[1],                        // prix
                        convertToLocalDate(row[2]),                 // datedebut
                        row[3] != null ? convertToLocalDate(row[3]) : null, // datefin
                        (String) row[4],                            // numerochassis
                        (String) row[5]                             // ville
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ReparationDTO getReparationById(Integer reparationID) {
        List<Object[]> results = reparationRepository.findByIdCustom(reparationID);

        if (results == null || results.isEmpty()) {
            throw new ResourceNotFoundException("Reparation not found with id: " + reparationID);
        }

        // Get the first row from the results
        Object[] row = results.get(0);

        // Map the row to ReparationDTO
        return mapRowToReparationDTO(row);
    }

    @Override
    public List<ReparationDTO> getReparationByNumeroChassis(String numeroChassis) {
        List<Object[]> rawResults = reparationRepository.findByNumeroChassisCustom(numeroChassis);
        return rawResults.stream()
                .map(row -> new ReparationDTO(
                        (Integer) row[0],                           // reparationid
                        (BigDecimal) row[1],                        // prix
                        convertToLocalDate(row[2]),                 // datedebut
                        row[3] != null ? convertToLocalDate(row[3]) : null, // datefin
                        (String) row[4],                            // numerochassis
                        (String) row[5]                             // ville
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Reparation createReparation(ReparationDTOSecond reparation) {
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

        // Check if the results are empty
        if (results == null || results.isEmpty()) {
            throw new ResourceNotFoundException("Reparation not found with id: " + reparationID);
        }

        // Assuming we are interested in the first result, similar to the getReparationById method
        Object[] row = results.get(0); // Get the first row from the result

        // Delete the reparation by its ID
        reparationRepository.deleteCustom(reparationID);
    }

    // Private helper methods

    /**
     * Converts a database row to a ReparationDTO object.
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