package com.sige.sistema_empenhos.dtos;

import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoRecordDto(
        @NotNull BigDecimal valorPagamento,
        @NotBlank String contaBancaria,
        @NotNull LocalDateTime dataPagamento,
        @NotNull Long idEmpenho
) {
}
