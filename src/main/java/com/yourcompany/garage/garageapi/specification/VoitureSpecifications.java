// VoitureSpecifications.java
package com.yourcompany.garage.garageapi.specification;

import com.yourcompany.garage.garageapi.entity.Voiture;
import com.yourcompany.garage.garageapi.entity.TypeBoiteVitesse;
import com.yourcompany.garage.garageapi.entity.TypeCarrosserie;
import com.yourcompany.garage.garageapi.entity.TypeCombustible;
import com.yourcompany.garage.garageapi.entity.TypeCouleurs;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VoitureSpecifications {

    public static Specification<Voiture> hasMarque(String marque) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("marque")), "%" + marque.toLowerCase() + "%");
    }

    public static Specification<Voiture> hasTypeCarrosserie(TypeCarrosserie typeCarrosserie) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("typeCarrosserie"), typeCarrosserie);
    }

    public static Specification<Voiture> hasCouleur(TypeCouleurs couleur) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("couleur"), couleur);
    }

    public static Specification<Voiture> hasTypeCombustible(TypeCombustible typeCombustible) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("typeCombustible"), typeCombustible);
    }

    public static Specification<Voiture> hasTypeBoiteVitesse(TypeBoiteVitesse typeBoiteVitesse) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("typeBoiteVitesse"), typeBoiteVitesse);
    }

    public static Specification<Voiture> isEnVente(Boolean enVente) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("enVente"), enVente);
    }

    public static Specification<Voiture> isNeuf(Boolean neuf) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("neuf"), neuf);
    }

    public static Specification<Voiture> prixGreaterThan(BigDecimal prixMin) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("prix"), prixMin);
    }

    public static Specification<Voiture> prixLessThan(BigDecimal prixMax) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("prix"), prixMax);
    }

    public static Specification<Voiture> dateFabricationBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("dateFabrication"), startDate, endDate);
    }

    public static Specification<Voiture> nombreKmGreaterThan(Integer nombreKm) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("nombreKm"), nombreKm);
    }
}