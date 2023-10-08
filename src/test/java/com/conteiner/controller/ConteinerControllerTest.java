package com.conteiner.controller;

import com.conteiner.entity.Conteiner;
import com.conteiner.enums.ConteinerCategoria;
import com.conteiner.enums.ConteinerStatus;
import com.conteiner.enums.ConteinerTipo;
import com.conteiner.repository.ConteinerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConteinerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ConteinerRepository conteinerRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        conteinerRepository.deleteAll();
    }

    @Test
    void getAllConteiners() throws Exception {
        List<Conteiner> conteiners = List.of(
                new Conteiner("Teste 1", ConteinerTipo.QUARENTA, ConteinerStatus.CHEIO, ConteinerCategoria.EXPORTACAO),
                new Conteiner("Teste 2", ConteinerTipo.VINTE, ConteinerStatus.CHEIO, ConteinerCategoria.IMPORTACAO),
                new Conteiner("Teste 3", ConteinerTipo.VINTE, ConteinerStatus.VAZIO, ConteinerCategoria.EXPORTACAO)
        );

        conteinerRepository.saveAll(conteiners);

        mockMvc.perform(get("/conteiner"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cliente").value("Teste 1"));
    }

    @Test
    void getConteinerById() throws Exception {
        Conteiner conteiner = new Conteiner("Teste 1", ConteinerTipo.QUARENTA, ConteinerStatus.CHEIO, ConteinerCategoria.EXPORTACAO);

        conteinerRepository.save(conteiner);

        mockMvc.perform(get("/conteiner/{id}", conteiner.getNumero()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente").value("Teste 1"));
    }

    @Test
    void createConteiner() throws Exception {
        Conteiner conteiner = new Conteiner("Teste 1", ConteinerTipo.QUARENTA, ConteinerStatus.CHEIO, ConteinerCategoria.EXPORTACAO);

        mockMvc.perform(post("/conteiner")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(conteiner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente").value("Teste 1"));
    }

    @Test
    void updateConteiner() throws Exception {
        Conteiner conteiner = new Conteiner("Teste 1", ConteinerTipo.QUARENTA, ConteinerStatus.CHEIO, ConteinerCategoria.EXPORTACAO);

        conteinerRepository.save(conteiner);

        Conteiner conteiner2 = new Conteiner("Teste 1", ConteinerTipo.VINTE, ConteinerStatus.VAZIO, ConteinerCategoria.IMPORTACAO);

        mockMvc.perform(put("/conteiner/{id}", conteiner.getNumero())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conteiner2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente").value("Teste 1"))
                .andExpect(jsonPath("$.tipo").value("VINTE"))
                .andExpect(jsonPath("$.status").value("VAZIO"))
                .andExpect(jsonPath("$.categoria").value("IMPORTACAO"));
    }

    @Test
    void deleteConteiner() throws Exception {
        Conteiner conteiner = new Conteiner("Teste 1", ConteinerTipo.QUARENTA, ConteinerStatus.CHEIO, ConteinerCategoria.EXPORTACAO);

        conteinerRepository.save(conteiner);


        mockMvc.perform(delete("/conteiner/{id}", conteiner.getNumero()))
                .andExpect(status().isOk());
    }
}