package com.sige.sistema_empenhos.repositories;

import com.sige.sistema_empenhos.entities.liquidacao.LiquidacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidacaoRepository extends JpaRepository<LiquidacaoEntity, Long> {
}
