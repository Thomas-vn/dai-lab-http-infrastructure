package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Lieu;
import java.util.List;
import java.util.Optional;

public interface LieuService {

    List<Lieu> getAllLieu();

    Optional<Lieu> getLieuById(Integer id);

    // --Insertion --

    int insertLieu(Lieu lieu);

    // --Suppression --

    int deleteLieu(Integer id);
}
