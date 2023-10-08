package com.conteiner.service;

import com.conteiner.DTOs.ConteinerDTO;
import com.conteiner.entity.Conteiner;
import com.conteiner.repository.ConteinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConteinerService {
    @Autowired
    private ConteinerRepository conteinerRepository;

    public List<Conteiner> getAllConteiners() {
        return conteinerRepository.findAll();
    }

    public Optional<Conteiner> getConteinerById(String id) {
        return conteinerRepository.findById(id);
    }

    public Conteiner saveConteiner(ConteinerDTO conteiner) {
        Conteiner newConteiner = new Conteiner();
        newConteiner.setTipo(conteiner.getTipo());
        newConteiner.setCategoria(conteiner.getCategoria());
        newConteiner.setStatus(conteiner.getStatus());
        newConteiner.setCliente(conteiner.getCliente());

        return conteinerRepository.save(newConteiner);
    }

    public Optional<Conteiner> updateConteiner(String id, ConteinerDTO conteiner) {
        return conteinerRepository.findById(id).map(c -> {
            c.setTipo(conteiner.getTipo());
            c.setCategoria(conteiner.getCategoria());
            c.setStatus(conteiner.getStatus());

            return conteinerRepository.save(c);
        });
    }

    public void deleteConteiner(String id) {
        conteinerRepository.deleteById(id);
    }
}
