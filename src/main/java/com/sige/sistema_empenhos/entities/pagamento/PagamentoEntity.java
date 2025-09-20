package com.sige.sistema_empenhos.entities.pagamento;

import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "TB_PAGAMENTO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal valorPagamento;
    private int contaBancaria;
    private LocalDateTime dataPagamento;

    @ManyToOne // empenho pode ter varios pagamentos
               // pagamento referencia-se apenas a 1 empenho
    @JoinColumn(name = "empenho_id")
    private EmpenhoEntity empenho;
}
