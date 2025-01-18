package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "personne")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Personne {

    @Id
    @Column(name = "noavs")
    private Long noAVS;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "datenaissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "sexe", nullable = false, length = 1)
    private Character sexe;

    @ManyToOne
    @JoinColumn(name = "lieuid")
    private Lieu lieu;
}