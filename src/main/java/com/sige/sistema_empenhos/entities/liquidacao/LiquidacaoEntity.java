package com.sige.sistema_empenhos.entities.liquidacao;

import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "TB_LIQUIDACAO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiquidacaoEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal valorLiquidacao;
    private LocalDateTime dataLiquidacao;

    @ManyToOne // empenho pode ter varias liquidacoes
               // liquidacao apenas de um empenho
    @JoinColumn(name = "empenho_id")
    private EmpenhoEntity empenho;

}
