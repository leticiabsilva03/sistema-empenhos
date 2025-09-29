package com.sige.sistema_empenhos.services;

import com.sige.sistema_empenhos.dtos.EmpenhoRecordDto;
import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import com.sige.sistema_empenhos.enums.EmpenhoStatus;
import com.sige.sistema_empenhos.repositories.EmpenhoRepository;
import com.sige.sistema_empenhos.exceptions.EmpenhoNotFoundException;
import com.sige.sistema_empenhos.exceptions.EmpenhoInvalidDeleteException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmpenhoService {

    private final EmpenhoRepository empenhoRepository;

    public EmpenhoService(EmpenhoRepository empenhoRepository){
        this.empenhoRepository = empenhoRepository;
    }

    @Transactional
    public EmpenhoEntity save(EmpenhoRecordDto empenhoRecordDto){
        if (empenhoRecordDto.valorEmpenho()==null
                || empenhoRecordDto.valorEmpenho().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O valor do empenho deve ser maior do que zero.");
        }
        EmpenhoEntity empenhoEntity = new EmpenhoEntity(
                empenhoRecordDto.descricaoEmpenho(),
                empenhoRecordDto.valorEmpenho(),
                EmpenhoStatus.ABERTO,
                empenhoRecordDto.dataEmpenho()
        );
        return empenhoRepository.save(empenhoEntity);
    }

    public List<EmpenhoEntity> findAll(){
        return empenhoRepository.findAll();
    }

    public EmpenhoEntity findById(Long id){
        return empenhoRepository.findById(id)
                .orElseThrow(()->new EmpenhoNotFoundException("Empenho não encontrado!"));
    }

    @Transactional
    public void delete(Long id){
        EmpenhoEntity empenho = empenhoRepository.findById(id)
                .orElseThrow(()->new EmpenhoNotFoundException("Empenho não encontrado!"));
        if (empenho.getStatusEmpenho() == EmpenhoStatus.LIQUIDADO){
            throw new EmpenhoInvalidDeleteException("Não é possível excluir empenho liquidado. Estorne a liquidação antes.");
        }
        if (empenho.getStatusEmpenho() == EmpenhoStatus.PAGO){
            throw new EmpenhoInvalidDeleteException("Não é possível excluir empenho pago. Estorne o pagamento e a liquidação antes.");
        }

        empenhoRepository.delete(empenho);
    }

}
