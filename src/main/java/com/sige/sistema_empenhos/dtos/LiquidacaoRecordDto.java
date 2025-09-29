package com.sige.sistema_empenhos.dtos;

import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LiquidacaoRecordDto(
        @NotNull BigDecimal valorLiquidacao,
        @NotNull LocalDateTime dataLiquidacao,
        @NotNull Long idEmpenho
) {
}
