package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Entity
@Table(name = "vendeur")
@PrimaryKeyJoinColumn(name = "noavs")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Vendeur extends Personnel {

    @OneToMany(mappedBy = "vendeur")
    private Set<Vente> ventes;

}
