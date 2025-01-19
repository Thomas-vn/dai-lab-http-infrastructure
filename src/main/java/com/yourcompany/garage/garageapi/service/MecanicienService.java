package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Mecanicien;
import java.util.List;
import java.util.Optional;

public interface MecanicienService {
    List<Mecanicien> getAllMecaniciens();
    Mecanicien createMecanicien(Mecanicien mecanicien);
    Optional<Mecanicien> getMecanicienByNoAVS(Long noAVS);
    Mecanicien updateMecanicien(Long noAVS, Mecanicien mecanicienDetails);
    void deleteMecanicien(Long noAVS);
}