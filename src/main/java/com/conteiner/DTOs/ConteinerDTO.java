package com.conteiner.DTOs;

import com.conteiner.enums.ConteinerCategoria;
import com.conteiner.enums.ConteinerStatus;
import com.conteiner.enums.ConteinerTipo;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ConteinerDTO {
    @NonNull
    private String cliente;

    @NonNull
    private ConteinerTipo tipo;

    @NonNull
    private ConteinerStatus status;

    @NonNull
    private ConteinerCategoria categoria;
}
