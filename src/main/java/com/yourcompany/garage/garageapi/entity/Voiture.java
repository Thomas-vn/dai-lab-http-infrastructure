package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Voiture")
@Data
public class Voiture {

    @Id
    @Column(name = "NumeroChassis", length = 17)
    private String numeroChassis;

    @Column(name = "Marque", nullable = false, length = 50)
    private String marque;

    @Enumerated(EnumType.STRING)
    @Column(name = "TypeCarrosserie", nullable = false, columnDefinition = "garage.typecarrosserie")
    private TypeCarrosserie typeCarrosserie;

    @Enumerated(EnumType.STRING)
    @Column(name = "Couleur", nullable = false, columnDefinition = "garage.typecouleurs")
    private TypeCouleurs couleur;

    @Column(name = "DateFabrication", nullable = false)
    private LocalDate dateFabrication;

    @Column(name = "NombrePlaces", nullable = false)
    private Integer nombrePlaces;

    @Column(name = "Prix", nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(name = "NombrePortes", nullable = false)
    private Integer nombrePortes;

    @Column(name = "Puissance", nullable = false)
    private Integer puissance;

    @Column(name = "DescriptionOptions", columnDefinition = "TEXT")
    private String descriptionOptions;

    @Column(name = "DateExpertise", nullable = false)
    private LocalDate dateExpertise;

    @Enumerated(EnumType.STRING)
    @Column(name = "TypeCombustible", nullable = false, columnDefinition = "garage.typecombustible")
    private TypeCombustible typeCombustible;

    @Column(name = "NombreKm", nullable = false)
    private Integer nombreKm;

    @Enumerated(EnumType.STRING)
    @Column(name = "TypeBoiteVitesse", nullable = false, columnDefinition = "garage.typeboitevitesse")
    private TypeBoiteVitesse typeBoiteVitesse;

    @Column(name = "Consommation", precision = 5, scale = 2)
    private BigDecimal consommation;

    @Column(name = "Neuf", nullable = false)
    private Boolean neuf;

    @Column(name = "Garantie", nullable = false)
    private Boolean garantie;

    @Column(name = "DateFinGarantie")
    private LocalDate dateFinGarantie;

    @Column(name = "EnVente", nullable = false)
    private Boolean enVente;

    @ManyToOne
    @JoinColumn(name = "Proprietaire")
    private Personne proprietaire;
}