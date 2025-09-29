package com.sige.sistema_empenhos.entities.liquidacao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "TB_LIQUIDACAO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LiquidacaoEntity implements Serializable {
    private static final long serialVersionUID = 240920251544L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorLiquidacao;

    @Column(name = "data_liquidada", nullable = false)
    private LocalDateTime dataLiquidacao;

    @ManyToOne (fetch = FetchType.LAZY) // empenho pode ter varias liquidacoes
               // liquidacao apenas de um empenho
    @JoinColumn(name = "empenho_id")
    @JsonIgnore
    private EmpenhoEntity empenho;

    public LiquidacaoEntity (BigDecimal valorLiquidacao, LocalDateTime dataLiquidacao, EmpenhoEntity empenho){
        this.valorLiquidacao = valorLiquidacao;
        this.dataLiquidacao = dataLiquidacao;
        this.empenho = empenho;
    }

}
