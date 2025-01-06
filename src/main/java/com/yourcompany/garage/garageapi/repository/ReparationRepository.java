package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Reparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReparationRepository extends JpaRepository<Reparation, Integer> {

    // Find all reparations
    @Query("SELECT r FROM Reparation r")
    List<Reparation> findAll();

    // Find all reparations by the car chassis number (NumeroChassis)
    @Query("SELECT r FROM Reparation r WHERE r.numeroChassis = :numeroChassis")
    List<Reparation> findByNumeroChassis(String numeroChassis);

    // Find all reparations by the city of the repair location
    @Query("SELECT r FROM Reparation r JOIN r.lieu l WHERE l.ville = :ville")
    List<Reparation> findByLieuVille(@Param("ville") String ville);

    // Find all reparations within a specific price range
    @Query("SELECT r FROM Reparation r WHERE r.prix BETWEEN :minPrice AND :maxPrice")
    List<Reparation> findByPrixBetween(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    // Find all reparations that started after a specific date
    @Query("SELECT r FROM Reparation r WHERE r.dateDebut > :dateDebut")
    List<Reparation> findByDateDebutAfter(@Param("dateDebut") String dateDebut);

    // Find all reparations that ended before a specific date
    @Query("SELECT r FROM Reparation r WHERE r.dateFin < :dateFin")
    List<Reparation> findByDateFinBefore(@Param("dateFin") String dateFin);

    // Find all reparations with no end date (i.e., DateFin is null)
    @Query("SELECT r FROM Reparation r WHERE r.dateFin IS NULL")
    List<Reparation> findByDateFinIsNull();

    // Find a single reparation by its ID
    @Query("SELECT r FROM Reparation r WHERE r.reparationID = :reparationID")
    Optional<Reparation> findByReparationID(Integer reparationID);


    // Find all reparations with a price greater than a given value
    @Query("SELECT r FROM Reparation r WHERE r.prix > :prix")
    List<Reparation> findByPrixGreaterThan(@Param("prix") Double prix);

    // Find all reparations sorted by price in descending order
    @Query("SELECT r FROM Reparation r ORDER BY r.prix DESC")
    List<Reparation> findAllOrderByPrixDesc();

    // Find all reparations for a specific car that were finished (DateFin is not null)
    @Query("SELECT r FROM Reparation r WHERE r.numeroChassis = :numeroChassis AND r.dateFin IS NOT NULL")
    List<Reparation> findCompletedReparationsByNumeroChassis(@Param("numeroChassis") String numeroChassis);

    // Find all reparations by their start and end dates range
    @Query("SELECT r FROM Reparation r WHERE r.dateDebut BETWEEN :startDate AND :endDate")
    List<Reparation> findByDateDebutBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);
}