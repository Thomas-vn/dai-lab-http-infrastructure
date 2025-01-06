package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Reparation;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReparationServiceImpl implements ReparationService {

    private final ReparationRepository reparationRepository;

    @Autowired
    public ReparationServiceImpl(ReparationRepository reparationRepository) {
        this.reparationRepository = reparationRepository;
    }

    // CRUD Operations

    @Override
    public List<Reparation> getAllReparations() {
        return reparationRepository.findAll();
    }

    @Override
    public Optional<Reparation> getReparationById(Integer reparationID) {
        return reparationRepository.findById(reparationID);
    }

    @Override
    public Reparation createReparation(Reparation reparation) {
        return reparationRepository.save(reparation);
    }

    @Override
    public Reparation updateReparation(Integer reparationID, Reparation reparationDetails) {
        Reparation reparation = reparationRepository.findById(reparationID)
                .orElseThrow(() -> new ResourceNotFoundException("Reparation not found for this id :: " + reparationID));

        reparation.setPrix(reparationDetails.getPrix());
        reparation.setDateDebut(reparationDetails.getDateDebut());
        reparation.setDateFin(reparationDetails.getDateFin());
        reparation.setNumeroChassis(reparationDetails.getNumeroChassis());
        reparation.setLieu(reparationDetails.getLieu());
        reparation.setServices(reparationDetails.getServices());

        return reparationRepository.save(reparation);
    }

    @Override
    public void deleteReparation(Integer reparationID) {
        Reparation reparation = reparationRepository.findById(reparationID)
                .orElseThrow(() -> new ResourceNotFoundException("Reparation not found for this id :: " + reparationID));

        reparationRepository.delete(reparation);
    }

    // Custom Query Operations

    @Override
    public List<Reparation> findByNumeroChassis(String numeroChassis) {
        return reparationRepository.findByNumeroChassis(numeroChassis);
    }

    @Override
    public List<Reparation> findByLieuVille(String ville) {
        return reparationRepository.findByLieuVille(ville);
    }

    @Override
    public List<Reparation> findByPrixBetween(Double minPrice, Double maxPrice) {
        return reparationRepository.findByPrixBetween(minPrice, maxPrice);
    }

    @Override
    public List<Reparation> findByDateDebutAfter(LocalDate dateDebut) {
        return reparationRepository.findByDateDebutAfter(dateDebut.toString()); // Adjust repository method if necessary
    }

    @Override
    public List<Reparation> findByDateFinBefore(LocalDate dateFin) {
        return reparationRepository.findByDateFinBefore(dateFin.toString()); // Adjust repository method if necessary
    }

    @Override
    public List<Reparation> findByDateFinIsNull() {
        return reparationRepository.findByDateFinIsNull();
    }

    @Override
    public Optional<Reparation> findByReparationID(Integer reparationID) {
        return reparationRepository.findByReparationID(reparationID);
    }

    @Override
    public List<Reparation> findByPrixGreaterThan(Double prix) {
        return reparationRepository.findByPrixGreaterThan(prix);
    }

    @Override
    public List<Reparation> findAllOrderByPrixDesc() {
        return reparationRepository.findAllOrderByPrixDesc();
    }

    @Override
    public List<Reparation> findCompletedReparationsByNumeroChassis(String numeroChassis) {
        return reparationRepository.findCompletedReparationsByNumeroChassis(numeroChassis);
    }

    @Override
    public List<Reparation> findByDateDebutBetween(LocalDate startDate, LocalDate endDate) {
        return reparationRepository.findByDateDebutBetween(startDate.toString(), endDate.toString()); // Adjust repository method if necessary
    }
}