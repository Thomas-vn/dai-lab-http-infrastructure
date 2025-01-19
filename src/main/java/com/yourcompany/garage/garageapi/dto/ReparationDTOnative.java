package com.yourcompany.garage.garageapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReparationDTOnative {

    @JsonProperty("reparationID")
    private Integer reparationID;

    @JsonProperty("prix")
    private BigDecimal prix;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("dateDebut")
    private LocalDate dateDebut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("dateFin")
    private LocalDate dateFin;

    @JsonProperty("numeroChassis")
    private String numeroChassis;

    @JsonProperty("lieuId")
    private Integer ville;


    public ReparationDTOnative(Integer reparationID, BigDecimal prix, LocalDate dateDebut, LocalDate dateFin, String numeroChassis, Integer ville) {
        this.reparationID = reparationID;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.numeroChassis = numeroChassis;
        this.ville = ville;
    }
}