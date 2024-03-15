package com.fiap.postech.gestaolocalidades.controller;


import com.fiap.postech.gestaolocalidades.model.Quarto;
import com.fiap.postech.gestaolocalidades.model.ReservaDTO;
import com.fiap.postech.gestaolocalidades.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/gestaoQuartos")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;
    @GetMapping
    public ResponseEntity<?> obterTodosQuartos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(quartoService.obterQuartos());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{idQuarto}")
    public ResponseEntity<?> obterQuartoPorId(@PathVariable UUID idQuarto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(quartoService.obterQuartoPorId(idQuarto));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarQuarto(@RequestBody Quarto novoQuarto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(quartoService.cadastrarQuarto(novoQuarto));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idQuarto}")
    public ResponseEntity<?> deletarQuarto(@PathVariable UUID idQuarto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(quartoService.deletarQuarto(idQuarto));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{idQuarto}")
    public ResponseEntity<?> atualizarQuarto(@PathVariable UUID idQuarto, @RequestBody Quarto atualizarQuarto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(quartoService.atualizarQuarto(idQuarto, atualizarQuarto));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/reservarQuarto")
    public ResponseEntity<?> reservarQuarto(@RequestBody ReservaDTO reservaDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(quartoService.reservarQuarto(reservaDTO.getIdQuarto(), reservaDTO.getDataInicio(), reservaDTO.getDataFim() ));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
