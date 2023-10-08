package com.conteiner.DTOs;

import com.conteiner.enums.TipoDeMovimentacao;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoDTO {
    @NonNull
    private String conteiner;

    @NonNull
    private TipoDeMovimentacao tipoDeMovitamentacao;
}
