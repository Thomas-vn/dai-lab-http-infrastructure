package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Voiture;
import com.yourcompany.garage.garageapi.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ventes")
public class CarSaleController {

    private final VoitureService voitureService;

    @Autowired
    public CarSaleController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping("/voitures")
    public ResponseEntity<List<Voiture>> searchVoitures(
            @RequestParam(required = false) String marque,
            @RequestParam(required = false) String typeCarrosserie,
            @RequestParam(required = false) BigDecimal prixMin,
            @RequestParam(required = false) BigDecimal prixMax,
            @RequestParam(required = false, defaultValue = "true") Boolean enVente) {
        
        List<Voiture> voitures = voitureService.getAllVoitures();
        
        // Filtrer les voitures selon les critères
        List<Voiture> filteredVoitures = voitures.stream()
            .filter(v -> enVente == null || v.getEnVente().equals(enVente))
            .filter(v -> marque == null || v.getMarque().toLowerCase().contains(marque.toLowerCase()))
            .filter(v -> typeCarrosserie == null || v.getTypeCarrosserie().toString().equalsIgnoreCase(typeCarrosserie))
            .filter(v -> prixMin == null || v.getPrix().compareTo(prixMin) >= 0)
            .filter(v -> prixMax == null || v.getPrix().compareTo(prixMax) <= 0)
            .collect(Collectors.toList());

        return ResponseEntity.ok(filteredVoitures);
    }

    @GetMapping("/voitures/{numeroChassis}")
    public ResponseEntity<Voiture> getVoitureDetails(@PathVariable String numeroChassis) {
        return voitureService.getVoitureByNumeroChassis(numeroChassis)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
