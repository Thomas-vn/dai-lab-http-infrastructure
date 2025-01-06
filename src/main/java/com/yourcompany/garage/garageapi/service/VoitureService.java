package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Voiture;

import java.util.List;
import java.util.Optional;

public interface VoitureService {

    List<Voiture> getAllVoitures();

    Optional<Voiture> getVoitureByNumeroChassis(String numeroChassis);

    Voiture createVoiture(Voiture voiture);

    Voiture updateVoiture(String numeroChassis, Voiture voitureDetails);

    void deleteVoiture(String numeroChassis);
}