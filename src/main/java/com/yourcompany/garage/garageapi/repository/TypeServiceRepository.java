package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Integer> {

    @Query(value = "SELECT * FROM type_service order by 1", nativeQuery = true)
    List<Object[]> getAllTypeServicesCustom();

    @Query(value = "INSERT INTO type_service (prix, description) VALUES (:prix, :description)", nativeQuery = true)
    void createTypeServiceCustom(Double prix, String description);

    @Query(value = "DELETE FROM type_service WHERE typeserviceid = :typeServiceID", nativeQuery = true)
    void deleteByIdCustom(Integer typeServiceID);

    @Query(value = "SELECT * FROM type_service WHERE prix >= :price AND prix <= :secondPrice", nativeQuery = true)
    List<Object[]> getAllTypeServicesByPriceCustom(Double price, Double secondPrice);
}
