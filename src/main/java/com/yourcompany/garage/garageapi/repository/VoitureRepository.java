// VoitureRepository.java
package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Voiture;
import com.yourcompany.garage.garageapi.entity.Personne;
import com.yourcompany.garage.garageapi.entity.TypeCarrosserie;
import com.yourcompany.garage.garageapi.entity.TypeCouleurs;
import com.yourcompany.garage.garageapi.entity.TypeCombustible;
import com.yourcompany.garage.garageapi.entity.TypeBoiteVitesse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, String> {

    /*
    // Find voitures by couleur
    @Query(value = "SELECT v FROM Voiture v WHERE v.couleur = :couleur", nativeQuery = true)
    List<Voiture> findByCouleur(@Param("couleur") String couleur);

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

    @Query("INSERT INTO Voiture v VALUES (:voiture)")
    void addCar(Voiture voiture);
    */

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

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO garage.voiture (numerochassis, marque, typecarrosserie, couleur, datefabrication, " +
            "nombreplaces, prix, nombreportes, puissance, descriptionoptions, dateexpertise, " +
            "typecombustible, nombrekm, typeboitevitesse, consommation, neuf, garantie, datefingarantie, " +
            "envente, proprietaire) " +
            "VALUES (:numeroChassis, :marque, CAST(:typeCarrosserie AS garage.typecarrosserie), " +
            "CAST(:couleur AS garage.typecouleurs), :dateFabrication, " +
            ":nombrePlaces, :prix, :nombrePortes, :puissance, :descriptionOptions, :dateExpertise, " +
            "CAST(:typeCombustible AS garage.typecombustible), :nombreKm, " +
            "CAST(:typeBoiteVitesse AS garage.typeboitevitesse), :consommation, :neuf, :garantie, :dateFinGarantie, " +
            ":enVente, :proprietaire)",
            nativeQuery = true)
    int insertVoiture(@Param("numeroChassis") String numeroChassis,
                      @Param("marque") String marque,
                      @Param("typeCarrosserie") String typeCarrosserie,
                      @Param("couleur") String couleur,
                      @Param("dateFabrication") LocalDate dateFabrication,
                      @Param("nombrePlaces") Integer nombrePlaces,
                      @Param("prix") BigDecimal prix,
                      @Param("nombrePortes") Integer nombrePortes,
                      @Param("puissance") Integer puissance,
                      @Param("descriptionOptions") String descriptionOptions,
                      @Param("dateExpertise") LocalDate dateExpertise,
                      @Param("typeCombustible") String typeCombustible,
                      @Param("nombreKm") Integer nombreKm,
                      @Param("typeBoiteVitesse") String typeBoiteVitesse,
                      @Param("consommation") BigDecimal consommation,
                      @Param("neuf") Boolean neuf,
                      @Param("garantie") Boolean garantie,
                      @Param("dateFinGarantie") LocalDate dateFinGarantie,
                      @Param("enVente") Boolean enVente,
                      @Param("proprietaire") Long proprietaire);
}