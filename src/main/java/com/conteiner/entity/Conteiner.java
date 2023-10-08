package com.conteiner.entity;

import com.conteiner.enums.ConteinerCategoria;
import com.conteiner.enums.ConteinerIdGenerator;
import com.conteiner.enums.ConteinerStatus;
import com.conteiner.enums.ConteinerTipo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CONTEINER")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Conteiner {
    @Column(name = "NUMERO")
    @Id
    @GeneratedValue(generator = "conteiner-id")
    @GenericGenerator(name = "conteiner-id", type = ConteinerIdGenerator.class)
    private String numero;

    @Column(name = "CLIENTE")
    @NonNull
    private String cliente;

    @Column(name = "TIPO")
    @Enumerated(EnumType.ORDINAL)
    @NonNull
    private ConteinerTipo tipo;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    @NonNull
    private ConteinerStatus status;

    @Column(name = "CATEGORIA")
    @Enumerated(EnumType.STRING)
    @NonNull
    private ConteinerCategoria categoria;
}
