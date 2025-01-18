package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Client;
import com.yourcompany.garage.garageapi.entity.Mecanicien;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import com.yourcompany.garage.garageapi.repository.ClientRepository;
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

     @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
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
            client.setDateAjout(clientDetails.getDateAjout());
            // Update other fields as necessary
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
}