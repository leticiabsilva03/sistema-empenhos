package com.sige.sistema_empenhos.repositories;

import com.sige.sistema_empenhos.entities.pagamento.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
}
