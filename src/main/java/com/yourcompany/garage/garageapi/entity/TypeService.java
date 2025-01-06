package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "type_service")
@Data
public class TypeService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeserviceid")
    private Integer typeServiceID;

    @Column(name = "prix", nullable = false)
    private BigDecimal prix;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // Relationships
    @OneToMany(mappedBy = "typeService")
    private Set<ServiceEntity> services;
}
