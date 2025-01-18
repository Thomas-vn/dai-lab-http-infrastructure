package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Personne;
import com.yourcompany.garage.garageapi.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/personne")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    // Get all Clients
    @GetMapping
    public ResponseEntity<List<Personne>> getAllPersonnes() {
        List<Personne> personnes = personneService.getAllPersonnes();
        return ResponseEntity.ok(personnes);
    }

    @PutMapping("/{noAVS}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long noAVS, @RequestBody Personne personneDetails) {
        Personne updatedPersonne = personneService.updatePersonne(noAVS, personneDetails);
        return ResponseEntity.ok(updatedPersonne);
    }


}
