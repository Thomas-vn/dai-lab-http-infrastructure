package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.dto.ReparationDTO;
import com.yourcompany.garage.garageapi.entity.Reparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReparationRepository extends JpaRepository<Reparation, Integer>{

    // Find all reparations
    @Query(value = "select r.reparationid,\n" +
            "       r.prix,\n" +
            "       r.datedebut,\n" +
            "       r.datefin,\n" +
            "       v.numerochassis,\n" +
            "       l.ville\n" +
            "        from reparation r\n" +
            "        join Voiture v using(numerochassis)\n" +
            "        join lieu l on r.lieuid = l.id", nativeQuery = true)
    List<Object[]> findAllCustom();

    // Find reparation by ID
    @Query(value = "SELECT r.reparationid, r.prix, r.datedebut, r.datefin, v.numerochassis, l.ville " +
            "FROM reparation r " +
            "JOIN Voiture v USING(numerochassis) " +
            "JOIN lieu l ON r.lieuid = l.id " +
            "WHERE r.reparationid = :reparationID", nativeQuery = true)
    List<Object[]> findByIdCustom(@Param("reparationID") Integer reparationID);


    @Query(value = "select r.reparationid,\n" +
            "       r.prix,\n" +
            "       r.datedebut,\n" +
            "       r.datefin,\n" +
            "       v.numerochassis,\n" +
            "       l.ville\n" +
            "        from reparation r\n" +
            "        join Voiture v using(numerochassis)\n" +
            "        join lieu l on r.lieuid = l.id\n" +
            "        where v.numerochassis = :numeroChassis", nativeQuery = true)
    List<Object[]> findByNumeroChassisCustom(String numeroChassis);


    @Query(value = "INSERT INTO reparation " +
            "VALUES (:reparationID, :prix, :dateDebut, :dateFin, :numeroChassis, :lieu)",
            nativeQuery = true)
    Reparation saveCustom(Integer reparationID,
                    BigDecimal prix,
                    LocalDate dateDebut,
                    LocalDate dateFin,
                    String numeroChassis,
                    Integer lieu);

    @Query(value = "DELETE FROM reparation WHERE reparationid = :reparationID", nativeQuery = true)
    void deleteCustom(Integer reparationID);

}