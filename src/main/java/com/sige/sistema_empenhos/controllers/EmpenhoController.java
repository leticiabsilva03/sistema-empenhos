package com.sige.sistema_empenhos.controllers;

import com.sige.sistema_empenhos.dtos.EmpenhoRecordDto;
import com.sige.sistema_empenhos.entities.empenho.EmpenhoEntity;
import com.sige.sistema_empenhos.services.EmpenhoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empenhos")
public class EmpenhoController {

    private final EmpenhoService empenhoService;
    public EmpenhoController (EmpenhoService empenhoService){
        this.empenhoService = empenhoService;
    }

    @PostMapping
    public ResponseEntity<EmpenhoEntity> saveEmpenho(@RequestBody @Valid EmpenhoRecordDto empenhoRecordDto) {
        EmpenhoEntity savedEmpenho = empenhoService.save(empenhoRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmpenho);
    }

    @GetMapping
    public ResponseEntity<List<EmpenhoEntity>> getAllEmpenhos (){
        return ResponseEntity.status(HttpStatus.OK).body(empenhoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<EmpenhoEntity> getOneEmpenho(@PathVariable(value = "id") Long id){
        EmpenhoEntity empenho = empenhoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(empenho);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteEmpenho(@PathVariable(value = "id") Long id){
        empenhoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Empenho excluído com sucesso!");
    }

}
