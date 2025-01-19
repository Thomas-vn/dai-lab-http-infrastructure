package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

    @Query(value = "select s.serviceid,\n" +
            "       s.commentaire,\n" +
            "       s.heuresdetravail,\n" +
            "       s.reparationid,\n" +
            "       s.noavsmecanicien,\n" +
            "       t.description\n" +
            "           from service s\n" +
            "              join type_service t on s.typeserviceid = t.typeserviceid;", nativeQuery = true)
    List<Object[]> getAllServicesCustom();

    @Query(value = "select s.serviceid,\n" +
            "       s.commentaire,\n" +
            "       s.heuresdetravail,\n" +
            "       s.reparationid,\n" +
            "       s.noavsmecanicien,\n" +
            "       t.description\n" +
            "           from service s\n" +
            "              join type_service t on s.typeserviceid = t.typeserviceid\n" +
            "           where s.serviceid = :serviceID", nativeQuery = true)
    List<Object[]> findByIdCustom(@Param("serviceID") Integer serviceID);


    @Query(value = "select s.serviceid,\n" +
            "       s.commentaire,\n" +
            "       s.heuresdetravail,\n" +
            "       s.reparationid,\n" +
            "       s.noavsmecanicien,\n" +
            "       t.description\n" +
            "           from service s\n" +
            "              join type_service t on s.typeserviceid = t.typeserviceid\n" +
            "           where s.noavsmecanicien = :noAVSMecanicien", nativeQuery = true)
    List<Object[]> findByNoAVSMecanicienCustom(Long noAVSMecanicien);


    @Query(value = "INSERT INTO service " +
            "VALUES (:serviceID, :commentaire, :heuresDeTravail, :reparationID, :noAVSMecanicien, :typeserviceid)",
            nativeQuery = true)
    Object[] saveCustom(Integer serviceID,
               String commentaire,
               Integer heuresDeTravail,
               Integer reparationID,
               Long noAVSMecanicien,
               Integer typeserviceid);

    @Query(value = "DELETE FROM service " +
            "WHERE serviceid = :serviceID",
            nativeQuery = true)
    void deleteByIdCustom(Integer serviceID);
}
