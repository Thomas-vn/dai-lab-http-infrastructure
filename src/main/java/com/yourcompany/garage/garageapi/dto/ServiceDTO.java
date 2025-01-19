package com.yourcompany.garage.garageapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ServiceDTO {
    @JsonProperty("serviceID")
    private Integer serviceID;

    @JsonProperty("commentaire")
    private String commentaire;

    @JsonProperty("heuresDeTravail")
    private Integer heuresDeTravail;

    @JsonProperty("reparationID")
    private Integer reparationID;

    @JsonProperty("noAVSMecanicien")
    private Long noAVSMecanicien;

    @JsonProperty("description")
    private String description;

    // Constructor with all fields
    public ServiceDTO(Integer serviceID, String commentaire, Integer heuresDeTravail, Integer reparationID, Long noAVSMecanicien, String description) {
        this.serviceID = serviceID;
        this.commentaire = commentaire;
        this.heuresDeTravail = heuresDeTravail;
        this.reparationID = reparationID;
        this.noAVSMecanicien = noAVSMecanicien;
        this.description = description;
    }
}