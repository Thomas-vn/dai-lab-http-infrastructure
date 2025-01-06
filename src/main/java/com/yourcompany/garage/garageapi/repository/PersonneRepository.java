package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    @Query("SELECT p FROM Personne p WHERE p.nom = :nom")
    List<Personne> findByNom(@Param("nom")String nom);

    @Query("SELECT p FROM Personne p WHERE p.nom = :nom")
    List<Personne> findPersonneByExactNom(@Param("nom") String nom);

    @Query("SELECT p FROM Personne p WHERE p.nom LIKE %:nom%")
    List<Personne> findByPrenomContainingIgnoreCase(@Param("prenom")String prenom);

    @Query("SELECT p FROM Personne p WHERE p.dateNaissance > :date")
    List<Personne> findByDateNaissanceAfter(@Param("date")LocalDate date);

    @Query("SELECT p FROM Personne p WHERE p.sexe = :sexe")
    List<Personne> findBySexeOrderByNomAsc(@Param("sexe")char sexe);
}