package com.fiap.postech.gestaolocalidades.service;

import com.fiap.postech.gestaolocalidades.model.Predio;
import com.fiap.postech.gestaolocalidades.repository.PredioRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;

import java.util.List;
import java.util.UUID;

@Service
public class PredioService {

    @Autowired
    private PredioRepository predioRepository;


    @Autowired
    private Validator validator;

    public List<Predio> obterPredios() {
        return predioRepository.findAll();

    }

    public Predio cadastrarPredio(Predio novoPredio) {
        var violations = validator.validate(novoPredio);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        novoPredio.setIdPredio(UUID.randomUUID());
        return predioRepository.save(novoPredio);
    }

    public UUID deletarPredio(UUID idPredio) {
        predioRepository.deleteById(idPredio);
        return idPredio;
    }

    public Predio atualizarPredio(UUID idPredio, Predio atualizarPredio) throws Exception {
        var violations = validator.validate(atualizarPredio);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        var predio = predioRepository.findById(idPredio);
        if (predio.isPresent()){
            var predioBusca = predio.get();
            predioBusca.setNomePredio(atualizarPredio.getNomePredio());
            predioBusca.setLocalidade(atualizarPredio.getLocalidade());
            atualizarPredio.setIdPredio(predioBusca.getIdPredio());
            predioRepository.save(predioBusca);
        }else{
            throw new Exception("Id " + idPredio + " não encontrado!");
        }

        return atualizarPredio;

    }
}
