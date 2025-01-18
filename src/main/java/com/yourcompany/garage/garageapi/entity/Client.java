package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "client")
@Data
@PrimaryKeyJoinColumn(name = "noavs")
public class Client extends Personne {

    @Column(name = "dateajout", nullable = false)
    private LocalDate dateAjout;

    public Client() {
        this.dateAjout = LocalDate.now(); // Définit la date d'ajout à la date actuelle
    }

    public Client(LocalDate dateAjout) {
        this.dateAjout = dateAjout; // Initialisation de dateAjout pour éviter les violations d'intégrité
    }
}