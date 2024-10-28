package com.clientservice.clientservice.service;

import com.clientservice.clientservice.controller.dtos.ClientBody;
import com.clientservice.clientservice.controller.dtos.ClientResponse;

import java.util.List;

public interface ClientService {

    boolean saveClient(ClientBody client);
    boolean updateClient(ClientBody client, Integer clientId);
    boolean deleteClient(Integer id);
    ClientResponse getClient(Integer id);
    List<ClientResponse> getClients();

}
