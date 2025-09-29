package com.sige.sistema_empenhos.services;

import com.sige.sistema_empenhos.dtos.LiquidacaoRecordDto;
import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import com.sige.sistema_empenhos.entities.liquidacao.LiquidacaoEntity;
import com.sige.sistema_empenhos.enums.EmpenhoStatus;
import com.sige.sistema_empenhos.exceptions.EmpenhoInvalidDeleteException;
import com.sige.sistema_empenhos.exceptions.EmpenhoInvalidStatusException;
import com.sige.sistema_empenhos.exceptions.EmpenhoNotFoundException;
import com.sige.sistema_empenhos.exceptions.LiquidacaoNotFoundException;
import com.sige.sistema_empenhos.repositories.EmpenhoRepository;
import com.sige.sistema_empenhos.repositories.LiquidacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LiquidacaoService {
    private final LiquidacaoRepository liquidacaoRepository;
    private final EmpenhoRepository empenhoRepository;

    public LiquidacaoService(LiquidacaoRepository liquidacaoRepository,
                             EmpenhoRepository empenhoRepository){
        this.liquidacaoRepository = liquidacaoRepository;
        this.empenhoRepository = empenhoRepository;
    }

    @Transactional
    public LiquidacaoEntity save(LiquidacaoRecordDto liquidacaoRecordDto){

        if(liquidacaoRecordDto.valorLiquidacao()==null
                || liquidacaoRecordDto.valorLiquidacao().compareTo(BigDecimal.ZERO)<=0) {
            throw new IllegalArgumentException("O valor a ser liquidado deve ser maior do que zero.");
        }

        EmpenhoEntity empenho = empenhoRepository.findById(liquidacaoRecordDto.idEmpenho())
                .orElseThrow(()->new EmpenhoNotFoundException("Empenho não encontrado!"));

        if(liquidacaoRecordDto.valorLiquidacao().compareTo(empenho.getValorEmpenho())>0){
            throw new IllegalArgumentException("O valor liquidado não pode ser maior do que o valor empenhado!");
        }

        if(empenho.getStatusEmpenho()==EmpenhoStatus.PAGO){
            throw new EmpenhoInvalidStatusException("Empenho está pago, não é possível liquidar!");
        }

        if (empenho.getStatusEmpenho()==EmpenhoStatus.LIQUIDADO){
            throw new EmpenhoInvalidStatusException("Empenho já está liquidado!");
        }

        LiquidacaoEntity liquidacaoEntity = new LiquidacaoEntity(
                liquidacaoRecordDto.valorLiquidacao(),
                liquidacaoRecordDto.dataLiquidacao(),
                empenho
        );
        empenho.setStatusEmpenho(EmpenhoStatus.LIQUIDADO);
        empenhoRepository.save(empenho);
        return liquidacaoRepository.save(liquidacaoEntity);
    }

    @Transactional
    public List<LiquidacaoEntity> findAll(){
        return liquidacaoRepository.findAll();
    }

    public LiquidacaoEntity findById(Long id){
        return liquidacaoRepository.findById(id)
                .orElseThrow(()->new LiquidacaoNotFoundException("Liquidação não encontrada."));
    }
    @Transactional
    public void delete (Long id){
        LiquidacaoEntity liquidacao = liquidacaoRepository.findById(id)
                .orElseThrow(()-> new LiquidacaoNotFoundException("Liquidação não encontrada."));
        EmpenhoEntity empenho = liquidacao.getEmpenho();
        if (empenho.getStatusEmpenho()==EmpenhoStatus.PAGO){
            throw new EmpenhoInvalidDeleteException("Não é possível estornar liquidação! Empenho está pago.");
        }
        empenho.setStatusEmpenho(EmpenhoStatus.ABERTO);
        empenhoRepository.save(empenho);
        liquidacaoRepository.delete(liquidacao);
    }
}
