package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Personne;
import com.yourcompany.garage.garageapi.repository.PersonneRepository;
import com.yourcompany.garage.garageapi.repository.VoitureRepository;
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
}
