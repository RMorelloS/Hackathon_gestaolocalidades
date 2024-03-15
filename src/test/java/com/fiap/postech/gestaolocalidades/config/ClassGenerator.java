package com.fiap.postech.gestaolocalidades.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.postech.gestaolocalidades.model.Localidade;
import com.fiap.postech.gestaolocalidades.model.Predio;
import com.fiap.postech.gestaolocalidades.model.Quarto;

import java.util.ArrayList;
import java.util.UUID;

public class ClassGenerator {
    public static Localidade geraLocalidade(){
        var localidade = new Localidade();
        localidade.setIdLocalidade(UUID.fromString("5b7f4a8b-7d15-4e83-b4c5-076a9c0cf948"));
        localidade.setNomeLocalidade("Chácara Santo Antonio");
        localidade.setListaAmenidades(new ArrayList<String>());
        localidade.setEndereco("Rua das Flores, 123");
        return localidade;
    }

    public static String geraLocalidadeJson(String classe) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return switch (classe) {
            case "localidade" -> objectMapper.writeValueAsString(geraLocalidade());
            case "predio" -> objectMapper.writeValueAsString(geraPredio());
            case "quarto" -> objectMapper.writeValueAsString(geraQuarto());
            default -> null;
        };

    }

    public static Predio geraPredio(){
        var predio = new Predio();
        predio.setIdPredio(UUID.fromString("5b7f4a8b-7d15-4e83-b4c5-076a9c0cf948"));
        var localidade = new Localidade();
        localidade.setIdLocalidade(UUID.randomUUID());
        predio.setLocalidade(localidade);
        predio.setNomePredio("Prédio das Flores");
        return predio;
    }

    public static Quarto geraQuarto(){
        var quarto = new Quarto();
        quarto.setIdQuarto(UUID.fromString("5b7f4a8b-7d15-4e83-b4c5-076a9c0cf948"));
        var predio = new Predio();
        predio.setIdPredio(UUID.randomUUID());
        quarto.setPredio(predio);
        quarto.setTipoQuarto("Single");
        quarto.setOutrosMoveis(new ArrayList<String>());
        quarto.setTotalCamas(4);
        quarto.setTotalPessoas(2);

        return quarto;
    }

}

