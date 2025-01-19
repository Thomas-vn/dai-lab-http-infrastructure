// VoitureServiceImpl.java
package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Voiture;
import com.yourcompany.garage.garageapi.entity.TypeCarrosserie;
import com.yourcompany.garage.garageapi.entity.TypeCouleurs;
import com.yourcompany.garage.garageapi.entity.TypeCombustible;
import com.yourcompany.garage.garageapi.entity.TypeBoiteVitesse;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.VoitureRepository;
import com.yourcompany.garage.garageapi.specification.VoitureSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public Voiture updateVoiture(String numeroChassis, Voiture voitureDetails) {
        Voiture existingVoiture = voitureRepository.findById(numeroChassis)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture not found with NumeroChassis: " + numeroChassis));

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
    }

    @Override
    public void deleteVoiture(String numeroChassis) {
        Voiture existingVoiture = voitureRepository.findById(numeroChassis)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture not found with NumeroChassis: " + numeroChassis));
        voitureRepository.delete(existingVoiture);
    }

    @Override
    public List<Voiture> getVoituresByMarque(String marque) {
        return voitureRepository.findByMarque(marque);
    }

    @Override
    public List<Voiture> getVoituresByTypeCarrosserie(String typeCarrosserie) {
        TypeCarrosserie type = TypeCarrosserie.valueOf(typeCarrosserie);
        return voitureRepository.findByTypeCarrosserie(type);
    }

    /*
    @Override
    public List<Voiture> getVoituresByCouleur(String couleur) {
        //TypeCouleurs couleurEnum = TypeCouleurs.valueOf(couleur);
        return voitureRepository.findByCouleur(couleur);
    }

    @Override
    public List<Voiture> getVoituresByTypeCombustible(String typeCombustible) {
        TypeCombustible combustible = TypeCombustible.valueOf(typeCombustible);
        return voitureRepository.findByTypeCombustible(combustible);
    }

    @Override
    public List<Voiture> getVoituresByTypeBoiteVitesse(String typeBoiteVitesse) {
        TypeBoiteVitesse boiteVitesse = TypeBoiteVitesse.valueOf(typeBoiteVitesse);
        return voitureRepository.findByTypeBoiteVitesse(boiteVitesse);
    }

    @Override
    public List<Voiture> getVoituresByEnVente(Boolean enVente) {
        return voitureRepository.findByEnVente(enVente);
    }

    @Override
    public List<Voiture> getVoituresByNeuf(Boolean neuf) {
        return voitureRepository.findByNeuf(neuf);
    }

    @Override
    public List<Voiture> getVoituresByProprietaireNoAVS(Long noAVS) {
        return voitureRepository.findByProprietaireNoAVS(noAVS);
    }

    @Override
    public List<Voiture> getVoituresByPrixGreaterThan(BigDecimal prix) {
        return voitureRepository.findByPrixGreaterThan(prix);
    }

    @Override
    public List<Voiture> getVoituresByPrixLessThan(BigDecimal prix) {
        return voitureRepository.findByPrixLessThan(prix);
    }

    @Override
    public List<Voiture> getVoituresByDateFabricationBetween(LocalDate startDate, LocalDate endDate) {
        return voitureRepository.findByDateFabricationBetween(startDate, endDate);
    }

    @Override
    public List<Voiture> getVoituresByNombreKmGreaterThan(Integer nombreKm) {
        return voitureRepository.findByNombreKmGreaterThan(nombreKm);
    }

    @Override
    public List<Voiture> getVoituresByCouleurAndEnVente(String couleur, Boolean enVente) {
        TypeCouleurs couleurEnum = TypeCouleurs.valueOf(couleur);
        return voitureRepository.findByCouleurAndEnVente(couleurEnum, enVente);
    }

    @Override
    public List<Voiture> getVoituresByTypeCarrosserieAndTypeCombustible(String typeCarrosserie, String typeCombustible) {
        TypeCarrosserie typeEnum = TypeCarrosserie.valueOf(typeCarrosserie);
        TypeCombustible combustibleEnum = TypeCombustible.valueOf(typeCombustible);
        return voitureRepository.findByTypeCarrosserieAndTypeCombustible(typeEnum, combustibleEnum);
    }
    */

    @Override
    public List<Voiture> searchVoitures(
            Optional<String> marque,
            Optional<TypeCarrosserie> typeCarrosserie,
            Optional<TypeCouleurs> couleur,
            Optional<TypeCombustible> typeCombustible,
            Optional<TypeBoiteVitesse> typeBoiteVitesse,
            Optional<Boolean> enVente,
            Optional<Boolean> neuf,
            Optional<BigDecimal> prixMin,
            Optional<BigDecimal> prixMax,
            Optional<LocalDate> startDate,
            Optional<LocalDate> endDate,
            Optional<Integer> nombreKm
    ) {
        Specification<Voiture> spec = Specification.where(null);

        if (marque.isPresent()) {
            spec = spec.and(VoitureSpecifications.hasMarque(marque.get()));
        }
        if (typeCarrosserie.isPresent()) {
            spec = spec.and(VoitureSpecifications.hasTypeCarrosserie(typeCarrosserie.get()));
        }
        if (couleur.isPresent()) {
            spec = spec.and(VoitureSpecifications.hasCouleur(couleur.get()));
        }
        if (typeCombustible.isPresent()) {
            spec = spec.and(VoitureSpecifications.hasTypeCombustible(typeCombustible.get()));
        }
        if (typeBoiteVitesse.isPresent()) {
            spec = spec.and(VoitureSpecifications.hasTypeBoiteVitesse(typeBoiteVitesse.get()));
        }
        if (enVente.isPresent()) {
            spec = spec.and(VoitureSpecifications.isEnVente(enVente.get()));
        }
        if (neuf.isPresent()) {
            spec = spec.and(VoitureSpecifications.isNeuf(neuf.get()));
        }
        if (prixMin.isPresent()) {
            spec = spec.and(VoitureSpecifications.prixGreaterThan(prixMin.get()));
        }
        if (prixMax.isPresent()) {
            spec = spec.and(VoitureSpecifications.prixLessThan(prixMax.get()));
        }
        if (startDate.isPresent() && endDate.isPresent()) {
            spec = spec.and(VoitureSpecifications.dateFabricationBetween(startDate.get(), endDate.get()));
        }
        if (nombreKm.isPresent()) {
            spec = spec.and(VoitureSpecifications.nombreKmGreaterThan(nombreKm.get()));
        }

        return voitureRepository.findAll((Sort) spec);
    }


 
    //--------INSERT--------

    @Override
    public void insertVoiture(Voiture voiture) {
        voitureRepository.insertVoiture(
            voiture.getNumeroChassis(),
            voiture.getMarque(),
            voiture.getTypeCarrosserie().name(),  
            voiture.getCouleur().name(),          
            voiture.getDateFabrication(),
            voiture.getNombrePlaces(),
            voiture.getPrix(),
            voiture.getNombrePortes(),
            voiture.getPuissance(),
            voiture.getDescriptionOptions(),
            voiture.getDateExpertise(),
            voiture.getTypeCombustible().name(),  
            voiture.getNombreKm(),
            voiture.getTypeBoiteVitesse().name(), 
            voiture.getConsommation(),
            voiture.getNeuf(),
            voiture.getGarantie(),
            voiture.getDateFinGarantie(),
            voiture.getEnVente(),
            voiture.getProprietaire().getNoAVS()
        );
    }
}