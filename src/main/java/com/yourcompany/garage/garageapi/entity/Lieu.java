package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lieu")
@Data
public class Lieu {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "rue", nullable = false, length = 100)
    private String rue;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "npa", nullable = false)
    private Integer npa;

    @Column(name = "ville", nullable = false, length = 100)
    private String ville;
}