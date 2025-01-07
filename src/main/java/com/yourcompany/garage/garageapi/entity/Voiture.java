package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "voiture")
@Data
public class Voiture {

    @Id
    @Column(name = "numerochassis", length = 17)
    private String numeroChassis;

    @Column(name = "marque", nullable = false, length = 50)
    private String marque;

    @Enumerated(EnumType.STRING)
    @Column(name = "typecarrosserie", nullable = false)
    private TypeCarrosserie typeCarrosserie;

    @Enumerated(EnumType.STRING)
    @Column(name = "couleur", nullable = false)
    private TypeCouleurs couleur;

    @Column(name = "datefabrication", nullable = false)
    private LocalDate dateFabrication;

    @Column(name = "nombreplaces", nullable = false)
    private Integer nombrePlaces;

    @Column(name = "prix", precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(name = "nombreportes", nullable = false)
    private Integer nombrePortes;

    @Column(name = "puissance", nullable = false)
    private Integer puissance;

    @Column(name = "descriptionoptions", columnDefinition = "TEXT")
    private String descriptionOptions;

    @Column(name = "dateexpertise", nullable = false)
    private LocalDate dateExpertise;

    @Enumerated(EnumType.STRING)
    @Column(name = "typecombustible", nullable = false)
    private TypeCombustible typeCombustible;

    @Column(name = "nombrekm", nullable = false)
    private Integer nombreKm;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeboitevitesse", nullable = false)
    private TypeBoiteVitesse typeBoiteVitesse;

    @Column(name = "consommation", precision = 5, scale = 2)
    private BigDecimal consommation;

    @Column(name = "neuf", nullable = false)
    private Boolean neuf;

    @Column(name = "garantie", nullable = false)
    private Boolean garantie;

    @Column(name = "datefingarantie")
    private LocalDate dateFinGarantie;

    @Column(name = "envente", nullable = false)
    private Boolean enVente;

    @ManyToOne
    @JoinColumn(name = "proprietaire")
    private Personne proprietaire;
}