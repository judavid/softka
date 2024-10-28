package com.clientservice.clientservice.controller;

import com.clientservice.clientservice.controller.dtos.ClientBody;
import com.clientservice.clientservice.controller.dtos.ClientResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ClientBody testClient;

    @BeforeEach
    public void setUp() {
        // Inicializa un cliente de prueba
        testClient = new ClientBody("Juan", "M", 30, "123456",
                "123 Street", "123456789", "", "true");
    }

    @Test
    public void testAddClient() throws Exception {
        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClient)))
                .andExpect(status().isOk())
               // .andExpect(jsonPath("$.saved").value(true))
                .andExpect(jsonPath("$.message").value("Saved Client"));
    }

    @Test
    public void testGetClient() throws Exception {

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClient)))
                .andExpect(status().isOk());

        List<ClientResponse> clientResponses = getClientResponses();

        mockMvc.perform(get("/client/"+clientResponses.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(clientResponses.get(0).getName()));
    }

    @Test
    public void testUpdateClient() throws Exception {
        // Primero crea el cliente
        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClient)))
                .andExpect(status().isOk());

        // Prepara el cliente actualizado
        ClientBody updatedClient = new ClientBody("Juan", "M", 30, "123456",
                "123 Street", "123456789", "", "true");

        List<ClientResponse> clientResponses = getClientResponses();

        mockMvc.perform(put("/client/"+clientResponses.get(0).getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
             //   .andExpect(jsonPath("$.updated").value(true))
                .andExpect(jsonPath("$.message").value("Updated client"));
    }

    @Test
    public void testDeleteClient() throws Exception {
        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClient)))
                .andExpect(status().isOk());

        List<ClientResponse> clientResponses = getClientResponses();

        // Luego intenta eliminar el cliente
        mockMvc.perform(delete("/client/"+clientResponses.get(0).getId()))
                .andExpect(status().isOk())
             //   .andExpect(jsonPath("$.deleted").value(true))
                .andExpect(jsonPath("$.message").value("Deleted Client"));
    }

    @Test
    public void testGetNonexistentClient() throws Exception {
        mockMvc.perform(get("/client/999")) // ID que no existe
                .andExpect(status().isNotFound());
    }

    private List<ClientResponse> getClientResponses() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/client"))
                .andExpect(status().isOk());
        List<ClientResponse> clientResponses = extractedValue(resultActions);
        return clientResponses;
    }

    private List<ClientResponse> extractedValue(ResultActions resultActions) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();

        // Convertir el JSON a un objeto Java
        return objectMapper.readValue(jsonResponse, new TypeReference<List<ClientResponse>>() {});
    }

}