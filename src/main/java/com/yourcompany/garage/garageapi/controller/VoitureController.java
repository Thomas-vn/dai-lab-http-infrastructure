package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Voiture;
import com.yourcompany.garage.garageapi.service.VoitureService;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/voitures")
public class VoitureController {

    private final VoitureService voitureService;

    @Autowired
    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    // GET all voitures
    @GetMapping
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureService.getAllVoitures();
        return ResponseEntity.ok(voitures);
    }

    // GET voiture by NumeroChassis
    @GetMapping("/{numeroChassis}")
    public ResponseEntity<Voiture> getVoitureByNumeroChassis(@PathVariable String numeroChassis) {
        return voitureService.getVoitureByNumeroChassis(numeroChassis)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture not found with NumeroChassis: " + numeroChassis));
    }

    // POST create new voiture
    @PostMapping
    public ResponseEntity<Voiture> createVoiture(@RequestBody Voiture voiture) {
        Voiture createdVoiture = voitureService.createVoiture(voiture);
        return new ResponseEntity<>(createdVoiture, HttpStatus.CREATED);
    }

    // PUT update existing voiture
    @PutMapping("/{numeroChassis}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable String numeroChassis, @RequestBody Voiture voitureDetails) {
        Voiture updatedVoiture = voitureService.updateVoiture(numeroChassis, voitureDetails);
        return ResponseEntity.ok(updatedVoiture);
    }

    // DELETE voiture
    @DeleteMapping("/{numeroChassis}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable String numeroChassis) {
        voitureService.deleteVoiture(numeroChassis);
        return ResponseEntity.noContent().build();
    }
}