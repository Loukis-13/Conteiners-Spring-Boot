package com.conteiner.controller;

import com.conteiner.DTOs.ConteinerDTO;
import com.conteiner.entity.Conteiner;
import com.conteiner.service.ConteinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("conteiner")
public class ConteinerController {
    @Autowired
    private ConteinerService conteinerService;

    @GetMapping
    public List<Conteiner> getAllConteiners() {
        return conteinerService.getAllConteiners();
    }

    @GetMapping("{id}")
    public ResponseEntity<Conteiner> getConteinerById(@PathVariable String id) {
        return conteinerService.getConteinerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Conteiner createConteiner(@RequestBody ConteinerDTO conteiner) {
        return conteinerService.saveConteiner(conteiner);
    }

    @PutMapping("{id}")
    public ResponseEntity<Conteiner> getConteinerById(@PathVariable String id, @RequestBody ConteinerDTO conteiner) {
        return conteinerService.updateConteiner(id, conteiner)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteConteiner(@PathVariable String id) {
        conteinerService.deleteConteiner(id);
        return ResponseEntity.ok("Conteiner deleted successfully");
    }
}
