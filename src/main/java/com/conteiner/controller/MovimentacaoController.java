package com.conteiner.controller;

import com.conteiner.DTOs.MovimentacaoDTO;
import com.conteiner.entity.Movimentacao;
import com.conteiner.enums.ConteinerCategoria;
import com.conteiner.enums.TipoDeMovimentacao;
import com.conteiner.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    @Operation(tags = {"Movimentacao"})
    public List<Movimentacao> getAllMovimentacoes(
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) TipoDeMovimentacao tipoDeMovimentacao,
            @RequestParam(required = false) ConteinerCategoria categoria
    ) {
        return movimentacaoService.getAllMovimentacoes(cliente, tipoDeMovimentacao, categoria);
    }

    @GetMapping("{id}")
    @Operation(tags = {"Movimentacao"})
    public ResponseEntity<Movimentacao> getMovimentacaoById(@PathVariable Long id) {
        return movimentacaoService.getMovimentacaoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(tags = {"Movimentacao"})
    public Movimentacao createMovimentacao(@RequestBody MovimentacaoDTO movimentacaoDTO) {
        return movimentacaoService.saveMovimentacao(movimentacaoDTO);
    }

    @PutMapping("{id}")
    @Operation(tags = {"Movimentacao"})
    public ResponseEntity<Movimentacao> getMovimentacaoById(@PathVariable Long id, @RequestBody MovimentacaoDTO movimentacaoDTO) {
        return movimentacaoService.updateMovimentacao(id, movimentacaoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @Operation(tags = {"Movimentacao"})
    public ResponseEntity<String> deleteMovimentacao(@PathVariable Long id) {
        movimentacaoService.deleteMovimentacao(id);
        return ResponseEntity.ok("Movimentacao deletada com sucesso");
    }

    @PutMapping("{id}/fim")
    @Operation(tags = {"Movimentacao"})
    public ResponseEntity<Movimentacao> setDataFimMovimentacao(@PathVariable Long id) {
        return movimentacaoService.setDataFim(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
