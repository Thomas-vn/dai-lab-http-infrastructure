package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Mecanicien;
import com.yourcompany.garage.garageapi.service.MecanicienService;
import com.yourcompany.garage.garageapi.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
public class MecanicienController {

    private final MecanicienService mecanicienService;
    private final PersonneService personneService;

    @Autowired
    public MecanicienController(MecanicienService mecanicienService, PersonneService personneService) {
        this.mecanicienService = mecanicienService;
        this.personneService = personneService;
    }

    @PostMapping
    public ResponseEntity<Mecanicien> createMechanic(@RequestBody Mecanicien mecanicien) {
        Mecanicien createdMecanicien = mecanicienService.createMecanicien(mecanicien);
        return ResponseEntity.ok(createdMecanicien);
    }

    @GetMapping
    public ResponseEntity<List<Mecanicien>> getAllMechanics() {
        try {
            List<Mecanicien> mechanics = mecanicienService.getAllMecaniciens();
            return ResponseEntity.ok(mechanics);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{noAVS}")
    public ResponseEntity<Mecanicien> getMechanicByNoAVS(@PathVariable Long noAVS) {
        return mecanicienService.getMecanicienByNoAVS(noAVS)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{noAVS}")
    public ResponseEntity<Mecanicien> updateMechanic(@PathVariable Long noAVS, @RequestBody Mecanicien mecanicienDetails) {
        Mecanicien updatedMecanicien = mecanicienService.updateMecanicien(noAVS, mecanicienDetails);
        return ResponseEntity.ok(updatedMecanicien);
    }

    @DeleteMapping("/{noAVS}")
    public ResponseEntity<Void> deleteMechanic(@PathVariable Long noAVS) {
        mecanicienService.deleteMecanicien(noAVS);
        return ResponseEntity.noContent().build();
    }
}