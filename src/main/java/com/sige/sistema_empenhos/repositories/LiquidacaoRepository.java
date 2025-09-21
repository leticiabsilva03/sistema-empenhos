package com.sige.sistema_empenhos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LiquidacaoRepository extends JpaRepository<LiquidacaoRepository, UUID> {
}
