package com.sige.sistema_empenhos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EmpenhoRecordDto(
        @NotBlank String descricaoEmpenho,
        @NotNull BigDecimal valorEmpenho,
        @NotNull LocalDateTime dataEmpenho) {

}
