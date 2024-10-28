package com.accountmovementservice.accountmovementservice.service.Client;

import com.accountmovementservice.accountmovementservice.dtos.client.ClientResponse;

public interface ClientService {

    ClientResponse getClient(Integer id);

}
