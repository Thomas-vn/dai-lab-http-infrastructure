package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Integer> {

    // Find all services performed by a specific mechanic (Mecanicien) based on NoAVS
    @Query("SELECT s FROM ServiceEntity s WHERE s.mecanicien.noAVS = :noAVS")
    List<ServiceEntity> findByMecanicienNoAVS(@Param("noAVS") Long noAVS);

    // Find all services for a specific repair based on ReparationID
    @Query("SELECT s FROM ServiceEntity s WHERE s.reparation.reparationID = :reparationID")
    List<ServiceEntity> findByReparationID(@Param("reparationID") Integer reparationID);

    // Find all services for a specific type of service (TypeServiceID)
    @Query("SELECT s FROM ServiceEntity s WHERE s.typeService.typeServiceID = :typeServiceID")
    List<ServiceEntity> findByTypeServiceTypeServiceID(@Param("typeServiceID") Integer typeServiceID);

    // Find a single service by its ServiceID
    Optional<ServiceEntity> findByServiceID(Integer serviceID);

    // Find all services performed by a specific mechanic (Mecanicien) in a specific time range (using HeuresDeTravail)
    @Query("SELECT s FROM ServiceEntity s WHERE s.mecanicien.noAVS = :noAVS AND s.heuresDeTravail BETWEEN :minHours AND :maxHours")
    List<ServiceEntity> findByMecanicienNoAVSAndHeuresDeTravailBetween(
            @Param("noAVS") Long noAVS,
            @Param("minHours") Integer minHours,
            @Param("maxHours") Integer maxHours);

    // Find all services for a specific repair (ReparationID) with a specific mechanic (NoAVS)
    @Query("SELECT s FROM ServiceEntity s WHERE s.reparation.reparationID = :reparationID AND s.mecanicien.noAVS = :noAVS")
    List<ServiceEntity> findByReparationIDAndMecanicienNoAVS(
            @Param("reparationID") Integer reparationID,
            @Param("noAVS") Long noAVS);

    // Find all services with a specific comment containing a keyword
    @Query("SELECT s FROM ServiceEntity s WHERE s.commentaire LIKE %:keyword%")
    List<ServiceEntity> findByCommentaireContaining(@Param("keyword") String keyword);

    // Find all services ordered by their hours of work (HeuresDeTravail) in descending order
    @Query("SELECT s FROM ServiceEntity s ORDER BY s.heuresDeTravail DESC")
    List<ServiceEntity> findAllOrderByHeuresDeTravailDesc();

    // Find all services performed by a mechanic (Mecanicien) for a specific type of service (TypeServiceID)
    @Query("SELECT s FROM ServiceEntity s WHERE s.mecanicien.noAVS = :noAVS AND s.typeService.typeServiceID = :typeServiceID")
    List<ServiceEntity> findByMecanicienNoAVSAndTypeServiceTypeServiceID(
            @Param("noAVS") Long noAVS,
            @Param("typeServiceID") Integer typeServiceID);

    // Find all services performed for a specific type of service (TypeServiceID) and for a specific repair (ReparationID)
    @Query("SELECT s FROM ServiceEntity s WHERE s.typeService.typeServiceID = :typeServiceID AND s.reparation.reparationID = :reparationID")
    List<ServiceEntity> findByTypeServiceAndReparationID(
            @Param("typeServiceID") Integer typeServiceID,
            @Param("reparationID") Integer reparationID);

    // Find the total number of hours worked for a specific repair (ReparationID)
    @Query("SELECT SUM(s.heuresDeTravail) FROM ServiceEntity s WHERE s.reparation.reparationID = :reparationID")
    Integer findTotalHeuresDeTravailByReparationID(@Param("reparationID") Integer reparationID);
}