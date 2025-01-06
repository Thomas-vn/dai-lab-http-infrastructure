// VoitureService.java
package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Voiture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoitureService {

    List<Voiture> getAllVoitures();

    Optional<Voiture> getVoitureByNumeroChassis(String numeroChassis);

    Voiture createVoiture(Voiture voiture);

    Voiture updateVoiture(String numeroChassis, Voiture voitureDetails);

    void deleteVoiture(String numeroChassis);

    // Additional methods for custom queries

    List<Voiture> getVoituresByMarque(String marque);

    List<Voiture> getVoituresByTypeCarrosserie(String typeCarrosserie);

    List<Voiture> getVoituresByCouleur(String couleur);

    List<Voiture> getVoituresByTypeCombustible(String typeCombustible);

    List<Voiture> getVoituresByTypeBoiteVitesse(String typeBoiteVitesse);

    List<Voiture> getVoituresByEnVente(Boolean enVente);

    List<Voiture> getVoituresByNeuf(Boolean neuf);

    List<Voiture> getVoituresByProprietaireNoAVS(Long noAVS);

    List<Voiture> getVoituresByPrixGreaterThan(BigDecimal prix);

    List<Voiture> getVoituresByPrixLessThan(BigDecimal prix);

    List<Voiture> getVoituresByDateFabricationBetween(LocalDate startDate, LocalDate endDate);

    List<Voiture> getVoituresByNombreKmGreaterThan(Integer nombreKm);

    List<Voiture> getVoituresByCouleurAndEnVente(String couleur, Boolean enVente);

    List<Voiture> getVoituresByTypeCarrosserieAndTypeCombustible(String typeCarrosserie, String typeCombustible);
}