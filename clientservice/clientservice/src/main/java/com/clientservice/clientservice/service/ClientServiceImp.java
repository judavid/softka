package com.clientservice.clientservice.service;

import com.clientservice.clientservice.controller.dtos.ClientBody;
import com.clientservice.clientservice.controller.dtos.ClientResponse;
import com.clientservice.clientservice.model.Client;
import com.clientservice.clientservice.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService{

    private final ClientRepository repository;

    @Autowired
    public ClientServiceImp(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public boolean saveClient(ClientBody client) {
        Client newClient = new Client(client.getName(), client.getGen(), client.getAge(),
                client.getIdentification(), client.getAddress(), client.getPhone(),
                client.getPassword(), client.getState());
        boolean saved = repository.save(newClient) != null;
        //TODO send created account
        return saved;
    }

    @Override
    @Transactional
    public boolean updateClient(ClientBody client, Integer id) {
        Optional<Client> byId = repository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }else{
            Client clientData = setClient(client, byId.get());
            return repository.save(clientData) != null;
        }

    }

    @Override
    @Transactional
    public boolean deleteClient(Integer id) {
        Optional<Client> byId = repository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }else{
            repository.delete(new Client(id));
            return true;
        }
        //TODO delete account
    }

    @Override
    public ClientResponse getClient(Integer id) {
        return repository.findById(id).map(client -> new ClientResponse(client.getId(), client.getName(), client.getGen(), client.getAge(),
                client.getIdentification(), client.getAddress(), client.getPhone(),
                client.getPassword(), client.getState())).orElse(null);
    }

    @Override
    public List<ClientResponse> getClients() {
        return repository.findAll().stream().map(client -> new ClientResponse(client.getId(), client.getName(), client.getGen(), client.getAge(),
                client.getIdentification(), client.getAddress(), client.getPhone(),
                client.getPassword(), client.getState())).toList();
    }

    private static Client setClient(ClientBody client, Client clientData) {
        clientData.setPassword(client.getPassword());
        clientData.setState(client.getState());
        clientData.setGen(client.getGen());
        clientData.setAddress(client.getAddress());
        clientData.setName(client.getName());
        clientData.setIdentification(client.getIdentification());
        clientData.setPhone(client.getPhone());
        clientData.setAge(client.getAge());
        return clientData;
    }
}
