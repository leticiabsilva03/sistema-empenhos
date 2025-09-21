package com.sige.sistema_empenhos.entities.empenho;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Table(name = "TB_EMPENHO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
// @Builder
public class EmpenhoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricaoEmpenho;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorEmpenho;

    @Enumerated(EnumType.STRING) // https://www.baeldung.com/jpa-persisting-enums-in-jpa
    @Column(name = "status", nullable = false)
    private Status statusEmpenho;

    @Column(name = "dat_criacao", nullable = false)
    private LocalDateTime dataEmpenho;

    public enum Status {
        ABERTO, LIQUIDADO, PAGO;
    }
}
