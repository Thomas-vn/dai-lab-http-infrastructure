package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.dto.IdDTO;
import com.yourcompany.garage.garageapi.dto.ReparationDTO;
import com.yourcompany.garage.garageapi.dto.ReparationDTOSecond;
import com.yourcompany.garage.garageapi.entity.Reparation;
import com.yourcompany.garage.garageapi.service.ReparationService;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reparations")
public class ReparationController {

    private final ReparationService reparationService;

    @Autowired
    public ReparationController(ReparationService reparationService) {
        this.reparationService = reparationService;
    }

    // ------------------- CRUD Operations -------------------

    /**
     * Get All Reparations
     * GET /api/reparations
     */
    @GetMapping
    public List<ReparationDTO> getAllReparations() {
        return reparationService.getAllReparations();
    }


    /**
     * Get Reparation by ID
     * GET /api/reparations
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReparationDTO> getReparationById(@PathVariable("id") int reparationID)
            throws ResourceNotFoundException {
        ReparationDTO reparation = reparationService.getReparationById(reparationID);
        return ResponseEntity.ok().body(reparation);
    }

    /**
     * Get Reparation by NumeroChassis
     * GET /api/reparations/voiture/{numeroChassis}
     */
    @GetMapping("/voiture/{numeroChassis}")
    public List<ReparationDTO> getReparationByNumeroChassis(@PathVariable("numeroChassis") String numeroChassis)
            throws ResourceNotFoundException {
        return reparationService.getReparationByNumeroChassis(numeroChassis);
    }

    /**
     * Create a New Reparation
     * POST /api/reparations
     */
    @PostMapping
    public boolean createReparation(@RequestBody ReparationDTOSecond reparation) {
        System.out.println("Received reparation: " + reparation);
        reparationService.createReparation(reparation);
        return true;
    }

    /**
     * Delete a Reparation
     * DELETE /api/reparations/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReparation(@PathVariable("id") int reparationID)
            throws ResourceNotFoundException {
        reparationService.deleteReparation(reparationID);
        return ResponseEntity.noContent().build();
    }
}