package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "vente")
@Data
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venteid")
    private Integer venteID;

    @Column(name = "datevente", nullable = false)
    private LocalDate dateVente;

    @Column(name = "rabais")
    private BigDecimal rabais;

    @Column(name = "prixreel", nullable = false)
    private BigDecimal prixReel;

    @ManyToOne
    @JoinColumn(name = "noavsclient", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "numerochassis", nullable = false)
    private Voiture voiture;

    @ManyToOne
    @JoinColumn(name = "noavsvendeur", nullable = false)
    private Vendeur vendeur;
}
