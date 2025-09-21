package com.sige.sistema_empenhos.entities.pagamento;

import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "TB_PAGAMENTO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorPagamento;

    @Column(name = "conta_bancaria", nullable = false, length = 255)
    private String contaBancaria;

    @Column(name = " data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @ManyToOne // empenho pode ter varios pagamentos
               // pagamento referencia-se apenas a 1 empenho
    @JoinColumn(name = "empenho_id")
    private EmpenhoEntity empenho;
}
