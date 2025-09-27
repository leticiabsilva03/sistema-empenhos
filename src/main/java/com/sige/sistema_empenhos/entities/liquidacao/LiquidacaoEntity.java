package com.sige.sistema_empenhos.entities.liquidacao;

import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "TB_LIQUIDACAO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiquidacaoEntity implements Serializable {
    private static final long serialVersionUID = 240920251544L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorLiquidacao;

    @Column(name = "data_liquidada", nullable = false)
    private LocalDateTime dataLiquidacao;

    @ManyToOne // empenho pode ter varias liquidacoes
               // liquidacao apenas de um empenho
    @JoinColumn(name = "empenho_id")
    private EmpenhoEntity empenho;

}
