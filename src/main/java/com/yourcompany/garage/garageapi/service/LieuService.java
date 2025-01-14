package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Lieu;
import java.util.List;

public interface LieuService {

    List<Lieu> getAllLieu();


    // --Insertion --

    int insertLieu(Lieu lieu);

    // --Suppression --

    int deleteLieu(Integer id);
}
