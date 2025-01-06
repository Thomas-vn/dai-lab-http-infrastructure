package com.yourcompany.garage.garageapi.repository;

import com.yourcompany.garage.garageapi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Client entity.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Finds all clients who were added after the specified date.
     *
     * @param date the date to compare with client's DateAjout
     * @return list of clients added after the specified date
     */
    @Query("SELECT c FROM Client c WHERE c.dateAjout > :date")
    List<Client> findByDateAjoutAfter(LocalDate date);

    /**
     * Finds all clients.
     *
     * @return list of all clients
     */
    @Query("SELECT c FROM Client c")
    List<Client> findAll();

    /**
     * Finds a client by their NoAVS.
     *
     * @param noAVS the NoAVS of the client
     * @return the client with the specified NoAVS
     */
    @Query("SELECT c FROM Client c WHERE c.noAVS = :noAVS")
    Optional<Client> findClientByNoAVS(Long noAVS);

    /**
     * Finds clients by their exact DateAjout.
     *
     * @param date the exact date of addition
     * @return list of clients added on the specified date
     */
    @Query("SELECT c FROM Client c WHERE c.dateAjout = :date")
    List<Client> findByDateAjout(LocalDate date);

    /**
     * Counts the number of clients added after the specified date.
     *
     * @param date the date to compare with client's DateAjout
     * @return count of clients added after the specified date
     */
    @Query("SELECT COUNT(c) FROM Client c WHERE c.dateAjout > :date")
    long countByDateAjoutAfter(LocalDate date);

    /**
     * Deletes all clients who were added before the specified date.
     *
     * @param date the cutoff date for deletion
     */
    @Query("DELETE FROM Client c WHERE c.dateAjout < :date")
    void deleteByDateAjoutBefore(LocalDate date);

    /**
     * Custom query to find clients whose NoAVS is in the specified range.
     * This uses JPQL (Java Persistence Query Language).
     *
     * @param startNoAVS the starting NoAVS
     * @param endNoAVS   the ending NoAVS
     * @return list of clients within the specified NoAVS range
     */
    @Query("SELECT c FROM Client c WHERE c.noAVS BETWEEN :startNoAVS AND :endNoAVS")
    List<Client> findClientsInNoAVSRange(@Param("startNoAVS") Long startNoAVS, @Param("endNoAVS") Long endNoAVS);


}