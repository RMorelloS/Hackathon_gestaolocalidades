package com.fiap.postech.gestaolocalidades.service;

import com.fiap.postech.gestaolocalidades.model.Localidade;
import com.fiap.postech.gestaolocalidades.repository.LocalidadeRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalidadeService {

    @Autowired
    private LocalidadeRepository localidadeRepository;
    @Autowired
    private Validator validator;
    public List<Localidade> obterLocalidades() {
        return localidadeRepository.findAll();
    }

    public Localidade cadastrarLocalidade(Localidade novaLocalidade) {
        var violations = validator.validate(novaLocalidade);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        novaLocalidade.setIdLocalidade(UUID.randomUUID());
        return localidadeRepository.save(novaLocalidade);
    }

    public UUID deletarLocalidade(UUID idLocalidade) {
        localidadeRepository.deleteById(idLocalidade);
        return idLocalidade;
    }

    public Localidade atualizarLocalidade(UUID idLocalidade, Localidade atualizarLocalidade) throws Exception {
        var violations = validator.validate(atualizarLocalidade);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        var localidade = localidadeRepository.findById(idLocalidade);
        if(localidade.isPresent()) {
            var localidadeAntiga = localidade.get();
            localidadeAntiga.setNomeLocalidade(atualizarLocalidade.getNomeLocalidade());
            localidadeAntiga.setEndereco(atualizarLocalidade.getEndereco());
            localidadeAntiga.setListaAmenidades(atualizarLocalidade.getListaAmenidades());
            atualizarLocalidade.setIdLocalidade(localidadeAntiga.getIdLocalidade());
        }else{
            throw new Exception("Id " + idLocalidade + " não encontrado");
        }
        return atualizarLocalidade;
    }
}
