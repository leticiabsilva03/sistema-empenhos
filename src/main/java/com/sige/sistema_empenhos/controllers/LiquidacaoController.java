package com.sige.sistema_empenhos.controllers;

import com.sige.sistema_empenhos.dtos.LiquidacaoRecordDto;
import com.sige.sistema_empenhos.entities.liquidacao.LiquidacaoEntity;
import com.sige.sistema_empenhos.services.LiquidacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/liquidacoes")
public class LiquidacaoController {

    private final LiquidacaoService liquidacaoService;
    public LiquidacaoController(LiquidacaoService liquidacaoService){
        this.liquidacaoService = liquidacaoService;
    }

    @PostMapping
    public ResponseEntity<LiquidacaoEntity> saveLiquidacao(@RequestBody @Valid LiquidacaoRecordDto liquidacaoRecordDto){
        LiquidacaoEntity savedLiquidacao = liquidacaoService.save(liquidacaoRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLiquidacao);
    }

    @GetMapping
    public ResponseEntity<List<LiquidacaoEntity>> getAllLiquidacoes (){
        return  ResponseEntity.status(HttpStatus.OK).body(liquidacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiquidacaoEntity> getOneLiquidacao(@PathVariable(value = "id") Long id){
        LiquidacaoEntity liquidacao = liquidacaoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(liquidacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> estornaLiquidacao(@PathVariable(value = "id") Long id){
        liquidacaoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Liquidação estornada com sucesso!");
    }
}
