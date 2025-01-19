package com.yourcompany.garage.garageapi.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TypeServiceDTOnative {

    @JsonProperty("typeServiceId")
    private Long typeServiceId;

    @JsonProperty("prix")
    private Double prix;

    @JsonProperty("description")
    private String description;

    public TypeServiceDTOnative(Long typeServiceId, Double prix, String description) {
        this.typeServiceId = typeServiceId;
        this.prix = prix;
        this.description = description;
    }

}
