package com.fiap.postech.gestaolocalidades.service;

import com.fiap.postech.gestaolocalidades.model.Quarto;
import com.fiap.postech.gestaolocalidades.repository.QuartoRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class QuartoService {

    @Autowired
    private Validator validator;

    @Autowired
    private QuartoRepository quartoRepository;


    public List<Quarto> obterQuartos() {
        return quartoRepository.findAll();
    }

    public Quarto cadastrarQuarto(Quarto novoQuarto) {
        var violations = validator.validate(novoQuarto);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        novoQuarto.setIdQuarto(UUID.randomUUID());
        quartoRepository.save(novoQuarto);
        return novoQuarto;
    }

    public UUID deletarQuarto(UUID idQuarto) {
        quartoRepository.deleteById(idQuarto);
        return idQuarto;
    }

    public Quarto atualizarQuarto(UUID idQuarto, Quarto atualizarQuarto) throws Exception {
        var violations = validator.validate(atualizarQuarto);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        var quarto = quartoRepository.findById(idQuarto);
        if (quarto.isPresent()){
            var quartoBusca = quarto.get();
            quartoBusca.setPredio(atualizarQuarto.getPredio());
            quartoBusca.setTotalCamas(atualizarQuarto.getTotalCamas());
            quartoBusca.setTotalPessoas(atualizarQuarto.getTotalPessoas());
            quartoBusca.setOutrosMoveis(atualizarQuarto.getOutrosMoveis());
            quartoBusca.setFimReserva(atualizarQuarto.getFimReserva());
            quartoBusca.setInicioReserva(atualizarQuarto.getInicioReserva());
            quartoBusca.setPrecoDiaria(atualizarQuarto.getPrecoDiaria());
            atualizarQuarto.setIdQuarto(quartoBusca.getIdQuarto());

            quartoRepository.save(quartoBusca);
        }else{
            throw new Exception("Id " + idQuarto + " não encontrado!");
        }

        return atualizarQuarto;

    }

    public Quarto obterQuartoPorId(UUID idQuarto) {
        var quarto = quartoRepository.findById(idQuarto);
        return quarto.get();
    }

    public Quarto reservarQuarto(UUID idQuarto, LocalDate dataInicio, LocalDate dataFim) {
        var quarto = quartoRepository.findById(idQuarto).get();
        quarto.setInicioReserva(dataInicio);
        quarto.setFimReserva(dataFim);
        quartoRepository.save(quarto);
        return quarto;
    }
}
