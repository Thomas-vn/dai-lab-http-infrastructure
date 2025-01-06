// VoitureRepository.java
package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Voiture;
import com.yourcompany.garage.garageapi.entity.Personne;
import com.yourcompany.garage.garageapi.entity.TypeCarrosserie;
import com.yourcompany.garage.garageapi.entity.TypeCouleurs;
import com.yourcompany.garage.garageapi.entity.TypeCombustible;
import com.yourcompany.garage.garageapi.entity.TypeBoiteVitesse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, String> {

    // Find all voitures
    @Query("SELECT v FROM Voiture v")
    List<Voiture> findAll();

    // Find voiture by NumeroChassis
    @Query("SELECT v FROM Voiture v WHERE v.numeroChassis = :numeroChassis")
    Optional<Voiture> findById(@Param("numeroChassis") String numeroChassis);

    // Find voitures by marque
    @Query("SELECT v FROM Voiture v WHERE v.marque = :marque")
    List<Voiture> findByMarque(@Param("marque") String marque);

    // Find voitures by typeCarrosserie
    @Query("SELECT v FROM Voiture v WHERE v.typeCarrosserie = :typeCarrosserie")
    List<Voiture> findByTypeCarrosserie(@Param("typeCarrosserie") TypeCarrosserie typeCarrosserie);

    // Find voitures by couleur
    @Query("SELECT v FROM Voiture v WHERE v.couleur = :couleur")
    List<Voiture> findByCouleur(@Param("couleur") TypeCouleurs couleur);

    // Find voitures by typeCombustible
    @Query("SELECT v FROM Voiture v WHERE v.typeCombustible = :typeCombustible")
    List<Voiture> findByTypeCombustible(@Param("typeCombustible") TypeCombustible typeCombustible);

    // Find voitures by typeBoiteVitesse
    @Query("SELECT v FROM Voiture v WHERE v.typeBoiteVitesse = :typeBoiteVitesse")
    List<Voiture> findByTypeBoiteVitesse(@Param("typeBoiteVitesse") TypeBoiteVitesse typeBoiteVitesse);

    // Find voitures by enVente
    @Query("SELECT v FROM Voiture v WHERE v.enVente = :enVente")
    List<Voiture> findByEnVente(@Param("enVente") Boolean enVente);

    // Find voitures by Neuf
    @Query("SELECT v FROM Voiture v WHERE v.neuf = :neuf")
    List<Voiture> findByNeuf(@Param("neuf") Boolean neuf);

    // Find voitures by proprietaire
    @Query("SELECT v FROM Voiture v WHERE v.proprietaire = :proprietaire")
    List<Voiture> findByProprietaire(@Param("proprietaire") Personne proprietaire);

    // Find voitures by proprietaire's NoAVS
    @Query("SELECT v FROM Voiture v WHERE v.proprietaire.noAVS = :noAVS")
    List<Voiture> findByProprietaireNoAVS(@Param("noAVS") Long noAVS);

    // Find voitures with prix greater than
    @Query("SELECT v FROM Voiture v WHERE v.prix > :prix")
    List<Voiture> findByPrixGreaterThan(@Param("prix") BigDecimal prix);

    // Find voitures with prix less than
    @Query("SELECT v FROM Voiture v WHERE v.prix < :prix")
    List<Voiture> findByPrixLessThan(@Param("prix") BigDecimal prix);

    // Find voitures by dateFabrication between
    @Query("SELECT v FROM Voiture v WHERE v.dateFabrication BETWEEN :startDate AND :endDate")
    List<Voiture> findByDateFabricationBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Find voitures by nombreKm greater than
    @Query("SELECT v FROM Voiture v WHERE v.nombreKm > :nombreKm")
    List<Voiture> findByNombreKmGreaterThan(@Param("nombreKm") Integer nombreKm);

    // Find voitures by couleur and enVente
    @Query("SELECT v FROM Voiture v WHERE v.couleur = :couleur AND v.enVente = :enVente")
    List<Voiture> findByCouleurAndEnVente(@Param("couleur") TypeCouleurs couleur, @Param("enVente") Boolean enVente);

    // Find voitures by typeCarrosserie and typeCombustible
    @Query("SELECT v FROM Voiture v WHERE v.typeCarrosserie = :typeCarrosserie AND v.typeCombustible = :typeCombustible")
    List<Voiture> findByTypeCarrosserieAndTypeCombustible(@Param("typeCarrosserie") TypeCarrosserie typeCarrosserie, @Param("typeCombustible") TypeCombustible typeCombustible);
}