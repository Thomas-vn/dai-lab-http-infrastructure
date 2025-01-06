package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, Integer> {

    // Find all Lieu by city (ville)
    @Query("SELECT l FROM Lieu l WHERE l.ville = :ville")
    List<Lieu> findByVille(String ville);

    // Find all Lieu by street (rue) name
    @Query("SELECT l FROM Lieu l WHERE l.rue = :rue")
    List<Lieu> findByRue(String rue);

    // Find a single Lieu by the combination of city and street
    @Query("SELECT l FROM Lieu l WHERE l.ville = :ville AND l.rue = :rue")
    Optional<Lieu> findByVilleAndRue(String ville, String rue);

    // Find all Lieu in a specific postal code (NPA)
    @Query("SELECT l FROM Lieu l WHERE l.npa = :npa")
    List<Lieu> findByNpa(int npa);

    // Custom query to find Lieu by city with a partial match (LIKE query)
    @Query("SELECT l FROM Lieu l WHERE l.ville LIKE %:ville%")
    List<Lieu> findByVilleContaining(@Param("ville") String ville);

    // Custom query to find Lieu by street and number
    @Query("SELECT l FROM Lieu l WHERE l.rue = :rue AND l.numero = :numero")
    Optional<Lieu> findByRueAndNumero(@Param("rue") String rue, @Param("numero") int numero);

    // Custom query to find all Lieu in a specific NPA and city
    @Query("SELECT l FROM Lieu l WHERE l.npa = :npa AND l.ville = :ville")
    List<Lieu> findByNpaAndVille(@Param("npa") int npa, @Param("ville") String ville);

    // Custom query to find all Lieu ordered by the street name (rue)
    @Query("SELECT l FROM Lieu l ORDER BY l.rue ASC")
    List<Lieu> findAllOrderByRue();
}