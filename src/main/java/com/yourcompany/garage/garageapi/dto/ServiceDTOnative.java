package com.yourcompany.garage.garageapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServiceDTOnative {
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

    @JsonProperty("typeserviceid")
    private Integer typeserviceid;

    // Constructor with all fields
    public ServiceDTOnative(Integer serviceID, String commentaire, Integer heuresDeTravail, Integer reparationID, Long noAVSMecanicien, Integer typeserviceid) {
        this.serviceID = serviceID;
        this.commentaire = commentaire;
        this.heuresDeTravail = heuresDeTravail;
        this.reparationID = reparationID;
        this.noAVSMecanicien = noAVSMecanicien;
        this.typeserviceid = typeserviceid;
    }
}