package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "service")
@Data
public class ServiceEntity { // Renamed to avoid conflict with Java's Service interface

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceID")
    private Integer serviceID;

    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    @Column(name = "heuresdetravail", nullable = false)
    private Integer heuresDeTravail;

    @ManyToOne
    @JoinColumn(name = "reparationid", nullable = false)
    private Reparation reparation;

    @ManyToOne
    @JoinColumn(name = "noavsmecanicien", nullable = false)
    private Mecanicien mecanicien;

    @ManyToOne
    @JoinColumn(name = "typeserviceid", nullable = false)
    private TypeService typeService;
}
