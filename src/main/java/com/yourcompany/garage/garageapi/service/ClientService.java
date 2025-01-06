package com.yourcompany.garage.garageapi.service;

import com.yourcompany.garage.garageapi.entity.Client;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);
    Optional<Client> getClientByNoAVS(Long noAVS);
    List<Client> getAllClients();
    Client updateClient(Long noAVS, Client clientDetails);
    void deleteClient(Long noAVS);
    List<Client> getClientsAddedAfter(LocalDate date);
}