package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Client;
import com.yourcompany.garage.garageapi.entity.Lieu;
import com.yourcompany.garage.garageapi.entity.Mecanicien;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.ClientRepository;
import com.yourcompany.garage.garageapi.repository.LieuRepository;
import com.yourcompany.garage.garageapi.repository.MecanicienRepository;
import com.yourcompany.garage.garageapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private final LieuRepository lieuRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, LieuRepository lieuRepository) {
        this.clientRepository = clientRepository;
        this.lieuRepository = lieuRepository;
    }

    @Override
    public Client createClient(Client client) {
        validateClient(client);
        
        try {
            // Vérification et récupération du lieu
            if (client.getLieu() != null && client.getLieu().getId() != null) {
                Integer lieuId = client.getLieu().getId();
                client.setLieu(lieuRepository.findById(lieuId)
                    .orElseThrow(() -> new IllegalArgumentException("Le lieu spécifié n'existe pas")));
            }
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du client", e);
        }
    }

    @Override
    public Optional<Client> getClientByNoAVS(Long noAVS) {
        return clientRepository.findClientByNoAVS(noAVS);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Long noAVS, Client clientDetails) {
        return clientRepository.findById(noAVS).map(client -> {
            // Update basic fields from Personne
            client.setNom(clientDetails.getNom());
            client.setPrenom(clientDetails.getPrenom());
            client.setDateNaissance(clientDetails.getDateNaissance());
            client.setSexe(clientDetails.getSexe());
            
            // Update Client specific fields
            client.setDateAjout(clientDetails.getDateAjout());
            
            // Update Lieu if provided
            if (clientDetails.getLieu() != null && clientDetails.getLieu().getId() != null) {
                Integer lieuId = clientDetails.getLieu().getId();
                client.setLieu(lieuRepository.findById(lieuId)
                    .orElseThrow(() -> new IllegalArgumentException("Le lieu spécifié n'existe pas")));
            }
            
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException("Client not found with NoAVS: " + noAVS));
    }

    @Override
    public void deleteClient(Long noAVS) {
        Client client = clientRepository.findById(noAVS)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with NoAVS: " + noAVS));
        clientRepository.delete(client);
    }

    @Override
    public List<Client> getClientsAddedAfter(LocalDate date) {
        return clientRepository.findByDateAjoutAfter(date);
    }

    private void validateClient(Client client) {
        if (client.getNom() == null || client.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du client ne peut pas être vide");
        }
        if (client.getPrenom() == null || client.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom du client ne peut pas être vide");
        }
        if (client.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance du client ne peut pas être vide");
        }
        if (client.getSexe() == null) {
            throw new IllegalArgumentException("Le sexe du client ne peut pas être vide");
        }
    }
}