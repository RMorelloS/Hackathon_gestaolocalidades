package com.fiap.postech.gestaolocalidades.controller;

import com.fiap.postech.gestaolocalidades.model.Predio;
import com.fiap.postech.gestaolocalidades.service.PredioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/gestaoPredios")
public class PredioController {

    @Autowired
    private PredioService predioService;
    @GetMapping
    public ResponseEntity<?> obterTodosPredios(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(predioService.obterPredios());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPredio(@RequestBody Predio novoPredio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(predioService.cadastrarPredio(novoPredio));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idPredio}")
    public ResponseEntity<?> deletarPredio(@PathVariable UUID idPredio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(predioService.deletarPredio(idPredio));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{idPredio}")
    public ResponseEntity<?> atualizarPredio(@PathVariable UUID idPredio, @RequestBody Predio atualizarPredio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(predioService.atualizarPredio(idPredio, atualizarPredio));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
