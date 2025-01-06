package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Voiture;
import com.yourcompany.garage.garageapi.repository.VoitureRepository;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureServiceImpl implements VoitureService {

    private final VoitureRepository voitureRepository;

    @Autowired
    public VoitureServiceImpl(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    @Override
    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    @Override
    public Optional<Voiture> getVoitureByNumeroChassis(String numeroChassis) {
        return voitureRepository.findById(numeroChassis);
    }

    @Override
    public Voiture createVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    @Override
    public Voiture updateVoiture(String numeroChassis, Voiture voitureDetails) {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(numeroChassis);
        if (optionalVoiture.isPresent()) {
            Voiture existingVoiture = optionalVoiture.get();
            existingVoiture.setMarque(voitureDetails.getMarque());
            existingVoiture.setTypeCarrosserie(voitureDetails.getTypeCarrosserie());
            existingVoiture.setCouleur(voitureDetails.getCouleur());
            existingVoiture.setDateFabrication(voitureDetails.getDateFabrication());
            existingVoiture.setNombrePlaces(voitureDetails.getNombrePlaces());
            existingVoiture.setPrix(voitureDetails.getPrix());
            existingVoiture.setNombrePortes(voitureDetails.getNombrePortes());
            existingVoiture.setPuissance(voitureDetails.getPuissance());
            existingVoiture.setDescriptionOptions(voitureDetails.getDescriptionOptions());
            existingVoiture.setDateExpertise(voitureDetails.getDateExpertise());
            existingVoiture.setTypeCombustible(voitureDetails.getTypeCombustible());
            existingVoiture.setNombreKm(voitureDetails.getNombreKm());
            existingVoiture.setTypeBoiteVitesse(voitureDetails.getTypeBoiteVitesse());
            existingVoiture.setConsommation(voitureDetails.getConsommation());
            existingVoiture.setNeuf(voitureDetails.getNeuf());
            existingVoiture.setGarantie(voitureDetails.getGarantie());
            existingVoiture.setDateFinGarantie(voitureDetails.getDateFinGarantie());
            existingVoiture.setEnVente(voitureDetails.getEnVente());
            existingVoiture.setProprietaire(voitureDetails.getProprietaire());

            return voitureRepository.save(existingVoiture);
        } else {
            throw new ResourceNotFoundException("Voiture not found with NumeroChassis: " + numeroChassis);
        }
    }

    @Override
    public void deleteVoiture(String numeroChassis) {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(numeroChassis);
        if (optionalVoiture.isPresent()) {
            voitureRepository.delete(optionalVoiture.get());
        } else {
            throw new ResourceNotFoundException("Voiture not found with NumeroChassis: " + numeroChassis);
        }
    }
}