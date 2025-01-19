package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Mecanicien;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.MecanicienRepository;
import com.yourcompany.garage.garageapi.repository.LieuRepository; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
@Transactional
public class MecanicienServiceImpl implements MecanicienService {

    private final MecanicienRepository mecanicienRepository;
    private final LieuRepository lieuRepository; 
    private static final Logger logger = LoggerFactory.getLogger(MecanicienServiceImpl.class);

    @Autowired
    public MecanicienServiceImpl(MecanicienRepository mecanicienRepository, LieuRepository lieuRepository) {
        this.mecanicienRepository = mecanicienRepository;
        this.lieuRepository = lieuRepository; 
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mecanicien> getAllMecaniciens() {
        return mecanicienRepository.findAll();
    }

    @Override
    @Transactional
    public Mecanicien createMecanicien(Mecanicien mecanicien) {
        logger.info("Tentative de création du mécanicien: {}", mecanicien);
        
        // Validation des données obligatoires
        validateMecanicien(mecanicien);

        try {
            // Vérification et récupération du lieu
            if (mecanicien.getLieu() != null && mecanicien.getLieu().getId() != null) {
                Integer lieuId = mecanicien.getLieu().getId();
                mecanicien.setLieu(lieuRepository.findById(lieuId)
                    .orElseThrow(() -> new IllegalArgumentException("Le lieu spécifié n'existe pas")));
            } else {
                throw new IllegalArgumentException("Un lieu valide doit être spécifié");
            }

            // Vérification du superviseur
            if (mecanicien.getSupervisor() != null) {
                if (mecanicien.getSupervisor().getNoAVS().equals(mecanicien.getNoAVS())) {
                    throw new IllegalArgumentException("Un mécanicien ne peut pas être son propre superviseur");
                }
                if (!mecanicienRepository.existsById(mecanicien.getSupervisor().getNoAVS())) {
                    throw new IllegalArgumentException("Le superviseur spécifié n'existe pas");
                }
            }

            // Sauvegarde du mécanicien
            Mecanicien created = mecanicienRepository.save(mecanicien);
            logger.info("Mécanicien créé avec succès: {}", created);
            return created;
        } catch (Exception e) {
            logger.error("Erreur lors de la création du mécanicien: {}", e.getMessage(), e);
            throw new IllegalStateException("Erreur lors de la création du mécanicien: " + e.getMessage(), e);
        }
    }

    private void validateMecanicien(Mecanicien mecanicien) {
        if (mecanicien.getNoAVS() == null) {
            throw new IllegalArgumentException("Le numéro AVS est obligatoire");
        }
        if (mecanicien.getNom() == null || mecanicien.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (mecanicien.getPrenom() == null || mecanicien.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (mecanicien.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire");
        }
        if (mecanicien.getSexe() == null) {
            throw new IllegalArgumentException("Le sexe est obligatoire");
        }
        if (mecanicien.getSalaire() == null || mecanicien.getSalaire().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le salaire doit être supérieur à 0");
        }
        if (mecanicien.getDateDebutContrat() == null) {
            throw new IllegalArgumentException("La date de début de contrat est obligatoire");
        }
        if (mecanicien.getDateFinContrat() != null && 
            mecanicien.getDateFinContrat().isBefore(mecanicien.getDateDebutContrat())) {
            throw new IllegalArgumentException("La date de fin de contrat doit être postérieure à la date de début");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mecanicien> getMecanicienByNoAVS(Long noAVS) {
        return mecanicienRepository.findByNoAVS(noAVS);
    }

    @Override
    public Mecanicien updateMecanicien(Long noAVS, Mecanicien mecanicienDetails) {
        Mecanicien mecanicien = mecanicienRepository.findById(noAVS)
            .orElseThrow(() -> new ResourceNotFoundException("Mécanicien non trouvé avec le noAVS: " + noAVS));

        // Mise à jour des champs
        mecanicien.setNom(mecanicienDetails.getNom());
        mecanicien.setPrenom(mecanicienDetails.getPrenom());
        mecanicien.setDateNaissance(mecanicienDetails.getDateNaissance());
        mecanicien.setSexe(mecanicienDetails.getSexe());
        mecanicien.setSalaire(mecanicienDetails.getSalaire());
        mecanicien.setPoste(mecanicienDetails.getPoste());
        mecanicien.setDateDebutContrat(mecanicienDetails.getDateDebutContrat());
        mecanicien.setDateFinContrat(mecanicienDetails.getDateFinContrat());

        // Mise à jour du lieu
        if (mecanicienDetails.getLieu() != null && mecanicienDetails.getLieu().getId() != null) {
            Integer lieuId = mecanicienDetails.getLieu().getId();
            mecanicien.setLieu(lieuRepository.findById(lieuId)
                .orElseThrow(() -> new IllegalArgumentException("Le lieu spécifié n'existe pas")));
        }

        // Mise à jour du superviseur si spécifié
        if (mecanicienDetails.getSupervisor() != null) {
            if (mecanicienDetails.getSupervisor().getNoAVS().equals(noAVS)) {
                throw new IllegalArgumentException("Un mécanicien ne peut pas être son propre superviseur");
            }
            if (!mecanicienRepository.existsById(mecanicienDetails.getSupervisor().getNoAVS())) {
                throw new IllegalArgumentException("Le superviseur spécifié n'existe pas");
            }
            mecanicien.setSupervisor(mecanicienDetails.getSupervisor());
        }

        return mecanicienRepository.save(mecanicien);
    }

    @Override
    public void deleteMecanicien(Long noAVS) {
        if (!mecanicienRepository.existsById(noAVS)) {
            throw new ResourceNotFoundException("Mécanicien non trouvé avec le NoAVS: " + noAVS);
        }
        mecanicienRepository.deleteById(noAVS);
    }
}