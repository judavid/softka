package com.clientservice.clientservice.service;

import com.clientservice.clientservice.controller.dtos.ClientBody;
import com.clientservice.clientservice.controller.dtos.ClientResponse;
import com.clientservice.clientservice.model.Client;
import com.clientservice.clientservice.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImpTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientServiceImp service;

    @Test
    void saveClientTest() {
        ClientBody clientBody = new ClientBody("John Doe", "M", 30, "123456", "123 Street", "1234567890", "password", "active");
        Client client = new Client("John Doe", "M", 30, "123456", "123 Street", "1234567890", "password", "active");

        when(repository.save(any(Client.class))).thenReturn(client);

        boolean result = service.saveClient(clientBody);

        assertTrue(result);
        verify(repository, times(1)).save(any(Client.class));
    }

    @Test
    void updateClientTest() {
        ClientBody clientBody = new ClientBody("John Doe", "M", 30, "123456", "123 Street", "1234567890", "password", "active");
        Client client = new Client("John Doe", "M", 30, "123456", "123 Street", "1234567890", "password", "active");

        when(repository.findById(1)).thenReturn(Optional.of(client));
        when(repository.save(any(Client.class))).thenReturn(client);

        boolean result = service.updateClient(clientBody, 1);

        assertTrue(result);
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).save(any(Client.class));
    }

    @Test
    void deleteClientTest() {
        Client client = new Client(1);

        when(repository.findById(1)).thenReturn(Optional.of(client));
        doNothing().when(repository).delete(any(Client.class));

        boolean result = service.deleteClient(1);

        assertTrue(result);
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).delete(any(Client.class));
    }

    @Test
    void getClientTest() {
        Client client = new Client( "John Doe", "M", 30, "123456", "123 Street", "1234567890", "password", "active");
        client.setId(1);
        ClientResponse clientResponse = new ClientResponse(1, "John Doe", "M", 30, "123456", "123 Street", "1234567890", "password", "active");

        when(repository.findById(1)).thenReturn(Optional.of(client));

        ClientResponse result = service.getClient(1);

        assertNotNull(result);
        assertEquals(clientResponse.getId(), result.getId());
        verify(repository, times(1)).findById(1);
    }

}