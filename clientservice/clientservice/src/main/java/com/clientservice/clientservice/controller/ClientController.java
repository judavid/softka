package com.clientservice.clientservice.controller;

import com.clientservice.clientservice.controller.dtos.ClientBody;
import com.clientservice.clientservice.controller.dtos.ClientResponse;
import com.clientservice.clientservice.controller.dtos.Response;
import com.clientservice.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping(value = "/client")
    public ResponseEntity<Response> addClient(@RequestBody ClientBody client) {
        boolean saved = clientService.saveClient(client);
        return saved ?
                ResponseEntity.ok(new Response(saved, "Saved Client")) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/client/{clientId}")
    public ResponseEntity<Response> updateClient(@RequestBody ClientBody client, @PathVariable Integer clientId) {
        boolean updated = clientService.updateClient(client, clientId);
        return updated ?
                ResponseEntity.ok(new Response(updated, "Updated client")) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/client/{clientId}")
    public ResponseEntity<Response> updateClient(@PathVariable Integer clientId) {
        boolean deleted = clientService.deleteClient(clientId);
        return deleted ?
                ResponseEntity.ok(new Response(deleted, "Deleted Client")) : ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/client/{clientId}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable Integer clientId) {
        ClientResponse client = clientService.getClient(clientId);
        return client != null ?
                ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/client")
    public ResponseEntity<List<ClientResponse>> getClients() {
        List<ClientResponse> clients = clientService.getClients();
        return !clients.isEmpty()  ?
                ResponseEntity.ok(clients) : ResponseEntity.notFound().build();
    }

}
