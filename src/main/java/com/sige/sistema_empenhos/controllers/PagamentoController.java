package com.sige.sistema_empenhos.controllers;

import com.sige.sistema_empenhos.dtos.PagamentoRecordDto;
import com.sige.sistema_empenhos.entities.pagamento.PagamentoEntity;
import com.sige.sistema_empenhos.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private  final PagamentoService pagamentoService;
    public PagamentoController(PagamentoService pagamentoService){
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoEntity> savePagamento(@RequestBody @Valid PagamentoRecordDto pagamentoRecordDto){
        PagamentoEntity savedPagamento = pagamentoService.save(pagamentoRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPagamento);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoEntity>> getAllPagamentos (){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoEntity> getOnePagamento(@PathVariable(value = "id") Long id){
        PagamentoEntity pagamento = pagamentoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> estornaPagamento(@PathVariable(value = "id")Long id){
        pagamentoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pagamento estornado com sucesso!");
    }
}
