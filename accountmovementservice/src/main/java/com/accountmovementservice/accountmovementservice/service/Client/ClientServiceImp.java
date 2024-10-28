package com.accountmovementservice.accountmovementservice.service.Client;

import com.accountmovementservice.accountmovementservice.dtos.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientServiceImp implements ClientService {

    private final RestTemplate restTemplate;
    private final String clientUrl;


    @Autowired
    public ClientServiceImp(RestTemplate restTemplate, @Value("${client.service.url}") String clientUrl) {
        this.restTemplate = restTemplate;
        this.clientUrl = clientUrl;
    }

    @Override
    public ClientResponse getClient(Integer id) {
        ClientResponse response = restTemplate.getForObject(clientUrl+"/client/"+id, ClientResponse.class);
        if (response != null) {
            return response;
        }
        return null;
    }
}
