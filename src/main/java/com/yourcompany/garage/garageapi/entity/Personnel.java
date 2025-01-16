package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name = "personnel")
@Data
@PrimaryKeyJoinColumn(name = "noavs")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personnel extends Personne {

    @Column(name = "salaire", nullable = false, precision = 10, scale = 2)
    private BigDecimal salaire;

    @Column(name = "poste", nullable = false, length = 50)
    private String poste;

    @Column(name = "datedebutcontrat", nullable = false)
    private LocalDate dateDebutContrat;

    @Column(name = "datefincontrat")
    private LocalDate dateFinContrat;

    @ManyToOne
    @JoinColumn(name = "supervisor")
    private Personnel supervisor;
}