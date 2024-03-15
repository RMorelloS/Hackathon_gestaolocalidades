package com.fiap.postech.gestaolocalidades.controller;

import com.fiap.postech.gestaolocalidades.model.Localidade;
import com.fiap.postech.gestaolocalidades.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/gestaoLocalidades")
public class LocalidadeController {

    @Autowired
    private LocalidadeService localidadeService;
    @GetMapping
    public ResponseEntity<?> obterTodasLocalidades(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(localidadeService.obterLocalidades());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarLocalidade(@RequestBody Localidade novaLocalidade){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(localidadeService.cadastrarLocalidade(novaLocalidade));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idLocalidade}")
    public ResponseEntity<?> deletarLocalidade(@PathVariable UUID idLocalidade){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(localidadeService.deletarLocalidade(idLocalidade));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{idLocalidade}")
    public ResponseEntity<?> atualizarLocalidade(@PathVariable UUID idLocalidade, @RequestBody Localidade atualizarLocalidade){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(localidadeService.atualizarLocalidade(idLocalidade, atualizarLocalidade));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
