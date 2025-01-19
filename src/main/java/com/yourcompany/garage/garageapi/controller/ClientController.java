package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Client;
import com.yourcompany.garage.garageapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Create a new Client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.ok(createdClient);
    }

    // Get all Clients
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            List<Client> clients = clientService.getAllClients();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get a Client by NoAVS
    @GetMapping("/{noAVS}")
    public ResponseEntity<Client> getClientByNoAVS(@PathVariable Long noAVS) {
        return clientService.getClientByNoAVS(noAVS)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a Client by NoAVS
    @PutMapping("/{noAVS}")
    public ResponseEntity<Client> updateClient(@PathVariable Long noAVS, @RequestBody Client clientDetails) {
        Client updatedClient = clientService.updateClient(noAVS, clientDetails);
        return ResponseEntity.ok(updatedClient);
    }

    // Delete a Client by NoAVS
    @DeleteMapping("/{noAVS}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long noAVS) {
        clientService.deleteClient(noAVS);
        return ResponseEntity.noContent().build();
    }

    // Get Clients added after a specific date
    @GetMapping("/addedAfter")
    public ResponseEntity<List<Client>> getClientsAddedAfter(@RequestParam String date) {
        LocalDate parsedDate = LocalDate.parse(date); // Ensure proper date format (e.g., YYYY-MM-DD)
        List<Client> clients = clientService.getClientsAddedAfter(parsedDate);
        return ResponseEntity.ok(clients);
    }
}