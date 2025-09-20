package com.sige.sistema_empenhos.entities.empenho;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "TB_EMPENHO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpenhoEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String descricaoEmpenho;
    private BigDecimal valorEmpenho;
    private String statusEmpenho;
    private LocalDateTime dataEmpenho;

}
