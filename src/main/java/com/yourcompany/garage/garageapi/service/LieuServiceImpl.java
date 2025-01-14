package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Lieu;
import com.yourcompany.garage.garageapi.repository.LieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LieuServiceImpl implements LieuService {

    private LieuRepository lieuRepository;

    @Autowired
    public LieuServiceImpl(LieuRepository lieuRepository) {
        this.lieuRepository = lieuRepository;
    }

    @Override
    public List<Lieu> getAllLieu() {
        return lieuRepository.findAll();
    }

    @Override
    public int insertLieu(Lieu lieu) {
        return lieuRepository.insertLieu(lieu.getId(), lieu.getRue(), lieu.getNumero(), lieu.getNpa(), lieu.getVille());
    }

    @Override
    public int deleteLieu(Integer id){
        return lieuRepository.deleteLieuQuery(id);
    }
}
