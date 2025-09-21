package com.sige.sistema_empenhos.repositories;

import com.sige.sistema_empenhos.entities.pagamento.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {
}
