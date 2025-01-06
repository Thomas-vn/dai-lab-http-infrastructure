package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Mecanicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MecanicienRepository extends JpaRepository<Mecanicien, Long> {

    // Find a Mecanicien by the AVS number (NoAVS)
    Optional<Mecanicien> findByNoAVS(Long noAVS);

    // Find all Mecaniciens who have a salary greater than a given value
    @Query("SELECT m FROM Mecanicien m WHERE m.salaire > :salaire")
    List<Mecanicien> findBySalaireGreaterThan(@Param("salaire") Double salaire);

    // Find all Mecaniciens who have a salary within a specific range
    @Query("SELECT m FROM Mecanicien m WHERE m.salaire BETWEEN :minSalary AND :maxSalary")
    List<Mecanicien> findBySalaireBetween(@Param("minSalary") Double minSalary, @Param("maxSalary") Double maxSalary);

    // Find the Mecanicien with the highest salary
    @Query("SELECT m FROM Mecanicien m ORDER BY m.salaire DESC")
    Optional<Mecanicien> findTopByOrderBySalaireDesc();

    // Find all Mecaniciens ordered by their salary (ascending)
    @Query("SELECT m FROM Mecanicien m ORDER BY m.salaire ASC")
    List<Mecanicien> findAllOrderBySalaireAsc();

}