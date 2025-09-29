package com.sige.sistema_empenhos.entities.pagamento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "TB_PAGAMENTO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoEntity implements Serializable {
    private static final long serialVersionUID = 240920251543L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorPagamento;

    @Column(name = "conta_bancaria", nullable = false, length = 255)
    private String contaBancaria;

    @Column(name = " data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @ManyToOne (fetch = FetchType.LAZY)// empenho pode ter varios pagamentos
               // pagamento referencia-se apenas a 1 empenho
    @JoinColumn(name = "empenho_id")
    @JsonIgnore
    private EmpenhoEntity empenho;

    public PagamentoEntity(BigDecimal valorPagamento, String contaBancaria, LocalDateTime dataPagamento, EmpenhoEntity empenho){
        this.valorPagamento = valorPagamento;
        this.contaBancaria = contaBancaria;
        this.dataPagamento = dataPagamento;
        this.empenho = empenho;
    }
}
