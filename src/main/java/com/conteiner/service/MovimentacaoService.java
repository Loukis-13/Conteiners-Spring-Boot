package com.conteiner.service;

import com.conteiner.DTOs.MovimentacaoDTO;
import com.conteiner.entity.Conteiner;
import com.conteiner.entity.Movimentacao;
import com.conteiner.enums.ConteinerCategoria;
import com.conteiner.enums.TipoDeMovimentacao;
import com.conteiner.repository.ConteinerRepository;
import com.conteiner.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private ConteinerRepository conteinerRepository;

    public List<Movimentacao> getAllMovimentacoes(String cliente, TipoDeMovimentacao tipoDeMovimentacao, ConteinerCategoria categoria){
        return movimentacaoRepository.findAll()
                .stream().filter(movimentacao -> {
                    if (cliente != null && !movimentacao.getConteiner().getCliente().equals(cliente)) {
                        return false;
                    }
                    if (tipoDeMovimentacao != null && !movimentacao.getTipoDeMovitamentacao().equals(tipoDeMovimentacao)) {
                        return false;
                    }
                    if (categoria != null && !movimentacao.getConteiner().getCategoria().equals(categoria)) {
                        return false;
                    }

                    return true;
                })
                .toList();
    }

    public Optional<Movimentacao> getMovimentacaoById(Long id){
        return movimentacaoRepository.findById(id);
    }

    public Movimentacao saveMovimentacao(MovimentacaoDTO movimentacaoDTO){
        Conteiner conteiner = conteinerRepository.findById(movimentacaoDTO.getConteiner())
                .orElseThrow(() -> new RuntimeException("Conteiner não encontrado"));

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setConteiner(conteiner);
        movimentacao.setTipoDeMovitamentacao(movimentacaoDTO.getTipoDeMovitamentacao());

        return movimentacaoRepository.save(movimentacao);
    }

    public Optional<Movimentacao> updateMovimentacao(Long id, MovimentacaoDTO movimentacao){
        return movimentacaoRepository.findById(id)
                .map(m -> {
                    m.setTipoDeMovitamentacao(movimentacao.getTipoDeMovitamentacao());
                    return movimentacaoRepository.save(m);
                });
    }

    public void deleteMovimentacao(Long id){
        movimentacaoRepository.deleteById(id);
    }

    public Optional<Movimentacao> setDataFim(Long  id){
        return movimentacaoRepository.findById(id)
                .map(m -> {
                    m.setFim(new Date());
                    return movimentacaoRepository.save(m);
                });
    }
}
