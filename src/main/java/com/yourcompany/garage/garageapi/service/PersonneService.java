package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Personne;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonneService {

    List<Personne> getAllPersonnes();
    
    Personne updatePersonne(Long noAVS, Personne personneDetails);


}

