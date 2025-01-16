package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import java.util.Set;

@Entity
@Table(name = "mecanicien")
@PrimaryKeyJoinColumn(name = "noavs")
@Inheritance(strategy = InheritanceType.JOINED)
public class Mecanicien extends Personnel {

    // Relationships
    @OneToMany(mappedBy = "mecanicien")
    private Set<ServiceEntity> services;

    // Getters and Setters

    public Set<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(Set<ServiceEntity> services) {
        this.services = services;
    }
}