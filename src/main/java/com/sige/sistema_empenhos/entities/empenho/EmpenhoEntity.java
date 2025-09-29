package com.sige.sistema_empenhos.entities.empenho;

import com.sige.sistema_empenhos.enums.EmpenhoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "TB_EMPENHO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class EmpenhoEntity implements Serializable {
    private static final long serialVersionUID = 240920251543L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricaoEmpenho;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorEmpenho;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmpenhoStatus statusEmpenho;

    @Column(name = "dat_criacao", nullable = false)
    private LocalDateTime dataEmpenho;

    public EmpenhoEntity (String descricaoEmpenho, BigDecimal valorEmpenho, EmpenhoStatus statusEmpenho, LocalDateTime dataEmpenho){
        this.descricaoEmpenho = descricaoEmpenho;
        this.valorEmpenho = valorEmpenho;
        this.statusEmpenho = statusEmpenho;
        this.dataEmpenho = dataEmpenho;
    }
}
