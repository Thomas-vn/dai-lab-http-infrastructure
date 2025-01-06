package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "reparation")
@Data
public class Reparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reparationid")
    private Integer reparationID;

    @Column(name = "prix", nullable = false)
    private BigDecimal prix;

    @Column(name = "datedebut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "datefin")
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "numerochassis", nullable = false)
    private Voiture numeroChassis;

    @ManyToOne
    @JoinColumn(name = "lieuid", nullable = false)
    private Lieu lieu;

    // Relationships
    @OneToMany(mappedBy = "reparation")
    private Set<ServiceEntity> services;

}