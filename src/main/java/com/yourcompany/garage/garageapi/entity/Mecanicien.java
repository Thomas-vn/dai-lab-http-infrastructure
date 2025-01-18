package com.yourcompany.garage.garageapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mecanicien")
@Data
@PrimaryKeyJoinColumn(name = "noavs")
public class Mecanicien extends Personnel {
}