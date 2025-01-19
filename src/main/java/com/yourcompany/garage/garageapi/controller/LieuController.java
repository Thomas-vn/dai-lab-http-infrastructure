package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Lieu;
import com.yourcompany.garage.garageapi.service.LieuService;
import com.yourcompany.garage.garageapi.dto.IdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/lieu")
public class LieuController {

    @Autowired
    private LieuService lieuService;

    @GetMapping
    public Iterable<Lieu> getAllLieu() {
        return lieuService.getAllLieu();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lieu> getLieuById(@PathVariable Integer id) {
        return lieuService.getLieuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public int insertLieu(@RequestBody Lieu lieu) {
        return lieuService.insertLieu(lieu);
    }

    @DeleteMapping
    public int deleteLieu(@RequestBody IdDTO id) {
        return lieuService.deleteLieu(id.getId());
    }
}
