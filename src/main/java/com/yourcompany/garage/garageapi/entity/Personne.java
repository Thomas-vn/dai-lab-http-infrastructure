package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "personne")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Personne {

    @Id
    @Column(name = "noavs")
    private Integer noAVS;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "datenaissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "sexe", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Sex sexe;

    @ManyToOne
    @JoinColumn(name = "lieuid")
    private Lieu lieu;


    private enum Sex { M, F};
}