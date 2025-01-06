package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Reparation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReparationService {

    // CRUD Operations
    List<Reparation> getAllReparations();
    Optional<Reparation> getReparationById(Integer reparationID);
    Reparation createReparation(Reparation reparation);
    Reparation updateReparation(Integer reparationID, Reparation reparationDetails);
    void deleteReparation(Integer reparationID);

    // Custom Query Operations
    List<Reparation> findByNumeroChassis(String numeroChassis);
    List<Reparation> findByLieuVille(String ville);
    List<Reparation> findByPrixBetween(Double minPrice, Double maxPrice);
    List<Reparation> findByDateDebutAfter(LocalDate dateDebut);
    List<Reparation> findByDateFinBefore(LocalDate dateFin);
    List<Reparation> findByDateFinIsNull();
    Optional<Reparation> findByReparationID(Integer reparationID);
    List<Reparation> findByPrixGreaterThan(Double prix);
    List<Reparation> findAllOrderByPrixDesc();
    List<Reparation> findCompletedReparationsByNumeroChassis(String numeroChassis);
    List<Reparation> findByDateDebutBetween(LocalDate startDate, LocalDate endDate);
}