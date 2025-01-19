package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "personnel")
@Data
@PrimaryKeyJoinColumn(name = "noavs")
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