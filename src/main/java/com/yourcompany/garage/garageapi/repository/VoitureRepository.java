package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Integer> {

    // Find a single car by its chassis number
    @Query("SELECT v FROM Voiture v")
    public List<Voiture> findAll();

    // Find a single car by its chassis number
    @Query("SELECT v FROM Voiture v WHERE v.numeroChassis = :numeroChassis")
    public Optional<Voiture> findById(String numeroChassis);

    // Find a car collection by its owner
    @Query("SELECT v FROM Voiture v WHERE v.proprietaire = :proprietaire")
    public List<Voiture> findByProprietaire(String proprietaire);
}

