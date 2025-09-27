package com.sige.sistema_empenhos.repositories;

import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpenhoRepository extends JpaRepository<EmpenhoEntity, Long> {
}
