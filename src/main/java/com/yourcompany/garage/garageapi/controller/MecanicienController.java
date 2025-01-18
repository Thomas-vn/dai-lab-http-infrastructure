package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Mecanicien;
import com.yourcompany.garage.garageapi.service.MecanicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
public class MecanicienController {

    private final MecanicienService mecanicienService;

    @Autowired
    public MecanicienController(MecanicienService mecanicienService) {
        this.mecanicienService = mecanicienService;
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
}