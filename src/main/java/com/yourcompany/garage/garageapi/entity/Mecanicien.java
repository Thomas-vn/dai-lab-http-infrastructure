package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mecanicien")
@PrimaryKeyJoinColumn(name = "noavs")
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