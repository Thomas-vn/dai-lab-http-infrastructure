package com.yourcompany.garage.garageapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReparationDTO {

    private Integer reparationID;
    private BigDecimal prix;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String numeroChassis;
    private String ville;


    public ReparationDTO(Integer reparationID, BigDecimal prix, LocalDate dateDebut, LocalDate dateFin, String numeroChassis, String ville) {
        this.reparationID = reparationID;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.numeroChassis = numeroChassis;
        this.ville = ville;
    }
}