package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Mecanicien;
import com.yourcompany.garage.garageapi.repository.MecanicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MecanicienServiceImpl implements MecanicienService {

    private final MecanicienRepository mecanicienRepository;

    @Autowired
    public MecanicienServiceImpl(MecanicienRepository mecanicienRepository) {
        this.mecanicienRepository = mecanicienRepository;
    }

    @Override
    public List<Mecanicien> getAllMecaniciens() {
        return mecanicienRepository.findAll();
    }
}