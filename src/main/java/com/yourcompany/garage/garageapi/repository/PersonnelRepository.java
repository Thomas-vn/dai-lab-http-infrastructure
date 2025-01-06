package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

    // Find Personnel by their job position (Poste)
    @Query("SELECT p FROM Personnel p WHERE p.poste = :poste")
    List<Personnel> findByPoste(String poste);

    // Find Personnel by salary greater than a certain value
    @Query("SELECT p FROM Personnel p WHERE p.salaire > :salaire")
    List<Personnel> findBySalaireGreaterThan(@Param("salaire") Double salaire);

    // Find Personnel by a specific supervisor (NoAVS)
    @Query("SELECT p FROM Personnel p WHERE p.supervisor = :supervisor")
    List<Personnel> findBySupervisor(@Param("supervisor") Long supervisor);

    // Find Personnel with contract ending after a specific date
    @Query("SELECT p FROM Personnel p WHERE p.dateFinContrat > :dateFinContrat")
    List<Personnel> findByDateFinContratAfter(@Param("dateFinContrat") String dateFinContrat);

    // Find Personnel who have a supervisor (i.e., those with a non-null supervisor)
    @Query("SELECT p FROM Personnel p WHERE p.supervisor IS NOT NULL")
    List<Personnel> findBySupervisorIsNotNull(@Param("supervisor") Long supervisor);

    // Find Personnel whose salary is within a given range (minSalary to maxSalary)
    @Query("SELECT p FROM Personnel p WHERE p.salaire BETWEEN :minSalary AND :maxSalary")
    List<Personnel> findBySalaireBetween(@Param("minSalary") Double minSalary, @Param("maxSalary") Double maxSalary);

    // Find the Personnel with the highest salary
    @Query("SELECT p FROM Personnel p ORDER BY p.salaire DESC")
    Optional<Personnel> findTopByOrderBySalaireDesc();

    // Find Personnel by their AVS number (NoAVS)
    @Query("SELECT p FROM Personnel p WHERE p.noAVS = :noAVS")
    Optional<Personnel> findByNoAVS(@Param("noAVS") Long noAVS);

    // Find all Personnel ordered by their starting date of the contract (DateDebutContrat)
    @Query("SELECT p FROM Personnel p ORDER BY p.dateDebutContrat ASC")
    List<Personnel> findAllOrderByDateDebutContratAsc();

    // Find Personnel who have a specific job title (Poste) and a salary greater than a specific value
    @Query("SELECT p FROM Personnel p WHERE p.poste = :poste AND p.salaire > :salaire")
    List<Personnel> findByPosteAndSalaireGreaterThan(@Param("poste") String poste, @Param("salaire") Double salaire);

    // Find all Personnel with contracts that ended before a specific date
    @Query("SELECT p FROM Personnel p WHERE p.dateFinContrat < :dateFinContrat")
    List<Personnel> findByDateFinContratBefore(@Param("dateFinContrat") String dateFinContrat);

}