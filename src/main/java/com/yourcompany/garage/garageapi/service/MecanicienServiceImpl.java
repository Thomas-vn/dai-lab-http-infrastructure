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
    public Mecanicien createMecanicien(Mecanicien mecanicien) {
        if (mecanicien.getLieu() != null) {
            lieuRepository.save(mecanicien.getLieu());
        }
        logger.info("Tentative de création du mécanicien: {}", mecanicien);
        try {
            Mecanicien created = mecanicienRepository.save(mecanicien);
            logger.info("Mécanicien créé avec succès: {}", created);
            logger.debug("Mécanicien créé avec les détails: {}", created);
            return created;
        } catch (Exception e) {
            logger.error("Erreur lors de la création du mécanicien: {}", e.getMessage(), e);
            throw new IllegalStateException("Erreur lors de la création du mécanicien: " + e.getMessage(), e);
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