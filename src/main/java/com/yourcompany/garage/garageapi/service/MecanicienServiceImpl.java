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
import java.time.LocalDate;

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
            // Sauvegarde du lieu d'abord s'il est nouveau
            if (mecanicien.getLieu() != null && mecanicien.getLieu().getId() == null) {
                mecanicien.setLieu(lieuRepository.save(mecanicien.getLieu()));
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
        if (mecanicien.getLieu() == null) {
            throw new IllegalArgumentException("Le lieu est obligatoire");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mecanicien> getMecanicienByNoAVS(Long noAVS) {
        return mecanicienRepository.findByNoAVS(noAVS);
    }

    @Override
    public Mecanicien updateMecanicien(Long noAVS, Mecanicien mecanicienDetails) {
        return mecanicienRepository.findById(noAVS)
            .map(mecanicien -> {
                if (mecanicienDetails.getNom() != null) {
                    mecanicien.setNom(mecanicienDetails.getNom());
                }
                if (mecanicienDetails.getPrenom() != null) {
                    mecanicien.setPrenom(mecanicienDetails.getPrenom());
                }
                if (mecanicienDetails.getSalaire() != null) {
                    mecanicien.setSalaire(mecanicienDetails.getSalaire());
                }
                if (mecanicienDetails.getPoste() != null) {
                    mecanicien.setPoste(mecanicienDetails.getPoste());
                }
                if (mecanicienDetails.getDateDebutContrat() != null) {
                    mecanicien.setDateDebutContrat(mecanicienDetails.getDateDebutContrat());
                }
                if (mecanicienDetails.getDateFinContrat() != null) {
                    mecanicien.setDateFinContrat(mecanicienDetails.getDateFinContrat());
                }
                if (mecanicienDetails.getSupervisor() != null) {
                    mecanicien.setSupervisor(mecanicienDetails.getSupervisor());
                }
                if (mecanicienDetails.getLieu() != null) {
                    mecanicien.setLieu(mecanicienDetails.getLieu());
                }
                return mecanicienRepository.save(mecanicien);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Mécanicien non trouvé avec le NoAVS: " + noAVS));
    }

    @Override
    public void deleteMecanicien(Long noAVS) {
        if (!mecanicienRepository.existsById(noAVS)) {
            throw new ResourceNotFoundException("Mécanicien non trouvé avec le NoAVS: " + noAVS);
        }
        mecanicienRepository.deleteById(noAVS);
    }
}