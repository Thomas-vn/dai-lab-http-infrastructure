package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Personne;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneServiceImpl implements PersonneService {

    private PersonneRepository personneRepository;

    @Autowired
    public PersonneServiceImpl(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    @Override
    public Personne updatePersonne(Long noAVS, Personne personneDetails) {
        return personneRepository.findById(noAVS)
            .map(personne -> {
                if (personneDetails.getNom() != null) {
                    personne.setNom(personneDetails.getNom());
                }
                if (personneDetails.getPrenom() != null) {
                    personne.setPrenom(personneDetails.getPrenom());
                }
                // Ajoutez d'autres champs à mettre à jour si nécessaire
                return personneRepository.save(personne);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Personne non trouvée avec le NoAVS: " + noAVS));
    }

    @Override
    public Personne addPersonne(Personne personne) {
        if (personne.getNoAVS() == null) {
            throw new IllegalArgumentException("Le numéro AVS est obligatoire");
        }
        if (personneRepository.existsById(personne.getNoAVS())) {
            throw new IllegalArgumentException("Une personne existe déjà avec ce numéro AVS");
        }
        return personneRepository.save(personne);
    }

    public Personne createPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public void deletePersonne(Long noAVS) {
        personneRepository.deleteById(noAVS);
    }
}
