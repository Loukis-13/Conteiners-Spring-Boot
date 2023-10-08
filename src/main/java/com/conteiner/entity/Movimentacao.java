package com.conteiner.entity;

import com.conteiner.enums.TipoDeMovimentacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "MOVIMENTACAO")
@Getter
@Setter
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIMENTACAO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "NUMERO")
    private Conteiner conteiner;

    @Column(name = "TIPO_DE_MOVITAMENTACAO")
    @Enumerated(EnumType.STRING)
    private TipoDeMovimentacao tipoDeMovitamentacao;

    @Column(name = "INICIO")
    @CreationTimestamp
    private Date inicio;

    @Column(name = "FIM")
    private Date fim;
}
