package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Lieu;
import com.yourcompany.garage.garageapi.service.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public int insertLieu(Lieu lieu) {
        return lieuService.insertLieu(lieu);
    }

}
