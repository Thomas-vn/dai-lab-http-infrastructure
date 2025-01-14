package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Lieu;
import com.yourcompany.garage.garageapi.service.LieuService;
import com.yourcompany.garage.garageapi.dto.IdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lieu")
public class LieuController {

    @Autowired
    private LieuService lieuService;

    @GetMapping
    public Iterable<Lieu> getAllLieu() {
        return lieuService.getAllLieu();
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
