package com.sige.sistema_empenhos.services;

import com.sige.sistema_empenhos.dtos.EmpenhoRecordDto;
import com.sige.sistema_empenhos.dtos.PagamentoRecordDto;
import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import com.sige.sistema_empenhos.entities.pagamento.PagamentoEntity;
import com.sige.sistema_empenhos.enums.EmpenhoStatus;
import com.sige.sistema_empenhos.exceptions.EmpenhoInvalidStatusException;
import com.sige.sistema_empenhos.exceptions.EmpenhoNotFoundException;
import com.sige.sistema_empenhos.exceptions.PagamentoNotFoundException;
import com.sige.sistema_empenhos.repositories.EmpenhoRepository;
import com.sige.sistema_empenhos.repositories.PagamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final EmpenhoRepository empenhoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            EmpenhoRepository empenhoRepository){
        this.pagamentoRepository = pagamentoRepository;
        this.empenhoRepository = empenhoRepository;
    }

    @Transactional
    public PagamentoEntity save(PagamentoRecordDto pagamentoRecordDto){

        EmpenhoEntity empenho = empenhoRepository.findById(pagamentoRecordDto.idEmpenho())
                .orElseThrow(()->new EmpenhoNotFoundException("Empenho não encontrado!"));

        if(pagamentoRecordDto.valorPagamento()==null
                || pagamentoRecordDto.valorPagamento().compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("O valor do pagamento deve ser maior do que zero.");
        }

        if(pagamentoRecordDto.valorPagamento().compareTo(empenho.getValorEmpenho())>0){
            throw new IllegalArgumentException("O valor pago não pode ser maior do que o valor empenhado!");
        }

        if(empenho.getStatusEmpenho()== EmpenhoStatus.ABERTO){
            throw new EmpenhoInvalidStatusException("Empenho não foi liquidado, não é possível pagar!");
        }
        if(empenho.getStatusEmpenho()==EmpenhoStatus.PAGO){
            throw new EmpenhoInvalidStatusException("Empenho já está pago!");
        }
        PagamentoEntity pagamentoEntity = new PagamentoEntity(
                pagamentoRecordDto.valorPagamento(),
                pagamentoRecordDto.contaBancaria(),
                pagamentoRecordDto.dataPagamento(),
                empenho
        );
        empenho.setStatusEmpenho(EmpenhoStatus.PAGO);
        empenhoRepository.save(empenho);
        return pagamentoRepository.save(pagamentoEntity);
    }

    @Transactional
    public List<PagamentoEntity> findAll(){
        return pagamentoRepository.findAll();
    }

    public PagamentoEntity findById(Long id){
        return pagamentoRepository.findById(id)
                .orElseThrow(()->new PagamentoNotFoundException("Pagamento não encontrado."));
    }

    @Transactional
    public void delete (Long id){
        PagamentoEntity pagamento = pagamentoRepository.findById(id)
                .orElseThrow(()->new PagamentoNotFoundException("Pagamento não encontrado."));
        EmpenhoEntity empenho = pagamento.getEmpenho();
        empenho.setStatusEmpenho(EmpenhoStatus.LIQUIDADO);
        empenhoRepository.save(empenho);
        pagamentoRepository.delete(pagamento);
    }
}
