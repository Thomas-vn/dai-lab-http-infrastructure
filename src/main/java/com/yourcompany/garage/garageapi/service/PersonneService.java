package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Personne;
import java.util.List;

public interface PersonneService {

    List<Personne> getAllPersonnes();
    
    Personne updatePersonne(Long noAVS, Personne personneDetails);
    
    Personne addPersonne(Personne nouvellePersonne);

}
