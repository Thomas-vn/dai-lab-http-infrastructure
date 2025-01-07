// VoitureController.java
package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.*;
import com.yourcompany.garage.garageapi.service.VoitureService;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        Voiture voiture = voitureService.getVoitureByNumeroChassis(numeroChassis)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture not found with NumeroChassis: " + numeroChassis));
        return ResponseEntity.ok(voiture);
    }

    // GET voitures by marque
    @GetMapping("/marque/{marque}")
    public ResponseEntity<List<Voiture>> getVoituresByMarque(@PathVariable String marque) {
        List<Voiture> voitures = voitureService.getVoituresByMarque(marque);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by typeCarrosserie
    @GetMapping("/typeCarrosserie/{typeCarrosserie}")
    public ResponseEntity<List<Voiture>> getVoituresByTypeCarrosserie(@PathVariable String typeCarrosserie) {
        List<Voiture> voitures = voitureService.getVoituresByTypeCarrosserie(typeCarrosserie);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by couleur
    @GetMapping("/couleur/{couleur}")
    public ResponseEntity<List<Voiture>> getVoituresByCouleur(@PathVariable String couleur) {
        List<Voiture> voitures = voitureService.getVoituresByCouleur(couleur);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by typeCombustible
    @GetMapping("/typeCombustible/{typeCombustible}")
    public ResponseEntity<List<Voiture>> getVoituresByTypeCombustible(@PathVariable String typeCombustible) {
        List<Voiture> voitures = voitureService.getVoituresByTypeCombustible(typeCombustible);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by typeBoiteVitesse
    @GetMapping("/typeBoiteVitesse/{typeBoiteVitesse}")
    public ResponseEntity<List<Voiture>> getVoituresByTypeBoiteVitesse(@PathVariable String typeBoiteVitesse) {
        List<Voiture> voitures = voitureService.getVoituresByTypeBoiteVitesse(typeBoiteVitesse);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by enVente
    @GetMapping("/enVente/{enVente}")
    public ResponseEntity<List<Voiture>> getVoituresByEnVente(@PathVariable Boolean enVente) {
        List<Voiture> voitures = voitureService.getVoituresByEnVente(enVente);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by Neuf
    @GetMapping("/neuf/{neuf}")
    public ResponseEntity<List<Voiture>> getVoituresByNeuf(@PathVariable Boolean neuf) {
        List<Voiture> voitures = voitureService.getVoituresByNeuf(neuf);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by proprietaire's NoAVS
    @GetMapping("/proprietaire/{noAVS}")
    public ResponseEntity<List<Voiture>> getVoituresByProprietaireNoAVS(@PathVariable Long noAVS) {
        List<Voiture> voitures = voitureService.getVoituresByProprietaireNoAVS(noAVS);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures with prix greater than
    @GetMapping("/prix/greaterThan/{prix}")
    public ResponseEntity<List<Voiture>> getVoituresByPrixGreaterThan(@PathVariable BigDecimal prix) {
        List<Voiture> voitures = voitureService.getVoituresByPrixGreaterThan(prix);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures with prix less than
    @GetMapping("/prix/lessThan/{prix}")
    public ResponseEntity<List<Voiture>> getVoituresByPrixLessThan(@PathVariable BigDecimal prix) {
        List<Voiture> voitures = voitureService.getVoituresByPrixLessThan(prix);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by dateFabrication between
    @GetMapping("/dateFabrication/between")
    public ResponseEntity<List<Voiture>> getVoituresByDateFabricationBetween(
            @RequestParam("start") String startDateStr,
            @RequestParam("end") String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<Voiture> voitures = voitureService.getVoituresByDateFabricationBetween(startDate, endDate);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by nombreKm greater than
    @GetMapping("/nombreKm/greaterThan/{nombreKm}")
    public ResponseEntity<List<Voiture>> getVoituresByNombreKmGreaterThan(@PathVariable Integer nombreKm) {
        List<Voiture> voitures = voitureService.getVoituresByNombreKmGreaterThan(nombreKm);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by couleur and enVente
    @GetMapping("/couleur/{couleur}/enVente/{enVente}")
    public ResponseEntity<List<Voiture>> getVoituresByCouleurAndEnVente(@PathVariable String couleur, @PathVariable Boolean enVente) {
        List<Voiture> voitures = voitureService.getVoituresByCouleurAndEnVente(couleur, enVente);
        return ResponseEntity.ok(voitures);
    }

    // GET voitures by typeCarrosserie and typeCombustible
    @GetMapping("/typeCarrosserie/{typeCarrosserie}/typeCombustible/{typeCombustible}")
    public ResponseEntity<List<Voiture>> getVoituresByTypeCarrosserieAndTypeCombustible(
            @PathVariable String typeCarrosserie,
            @PathVariable String typeCombustible) {
        List<Voiture> voitures = voitureService.getVoituresByTypeCarrosserieAndTypeCombustible(typeCarrosserie, typeCombustible);
        return ResponseEntity.ok(voitures);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Voiture>> searchVoitures(
            @RequestParam Optional<String> marque,
            @RequestParam Optional<TypeCarrosserie> typeCarrosserie,
            @RequestParam Optional<TypeCouleurs> couleur,
            @RequestParam Optional<TypeCombustible> typeCombustible,
            @RequestParam Optional<TypeBoiteVitesse> typeBoiteVitesse,
            @RequestParam Optional<Boolean> enVente,
            @RequestParam Optional<Boolean> neuf,
            @RequestParam Optional<BigDecimal> prixMin,
            @RequestParam Optional<BigDecimal> prixMax,
            @RequestParam Optional<LocalDate> startDate,
            @RequestParam Optional<LocalDate> endDate,
            @RequestParam Optional<Integer> nombreKm
    ) {
        List<Voiture> result = voitureService.searchVoitures(
                marque,
                typeCarrosserie,
                couleur,
                typeCombustible,
                typeBoiteVitesse,
                enVente,
                neuf,
                prixMin,
                prixMax,
                startDate,
                endDate,
                nombreKm
        );
        return ResponseEntity.ok(result);
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