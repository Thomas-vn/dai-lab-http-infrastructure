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
}
