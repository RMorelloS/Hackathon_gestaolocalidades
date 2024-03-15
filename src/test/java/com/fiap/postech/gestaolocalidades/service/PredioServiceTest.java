package com.fiap.postech.gestaolocalidades.service;


import com.fiap.postech.gestaolocalidades.config.ClassGenerator;
import com.fiap.postech.gestaolocalidades.model.Predio;
import com.fiap.postech.gestaolocalidades.repository.PredioRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@WebMvcTest(controllers=PredioService.class)
public class PredioServiceTest {


    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private PredioRepository predioRepository;


    @Autowired
    private PredioService predioService;


    private Predio mockPredio;

    public PredioServiceTest(){
        mockPredio = ClassGenerator.geraPredio();
    }

    @Test
    public void devePermitirCadastrarPredio(){
        Mockito.when(predioRepository.save(Mockito.any())).thenReturn(mockPredio);

        var resultado = predioService.cadastrarPredio(mockPredio);
        assertEquals(mockPredio.getIdPredio(), resultado.getIdPredio());
    }

    @Test
    public void deveRetornarErroQuandoCadastrarPredioNomeNulo(){
        var predioNomeNulo = mockPredio;
        predioNomeNulo.setNomePredio(null);
        assertThrows(ConstraintViolationException.class, () -> {
            predioService.cadastrarPredio(predioNomeNulo);
        });

    }


    @Test
    public void devePermitirObterPredios(){
        List<Predio> listaPredios = new ArrayList<>();
        Mockito.when(predioRepository.findAll()).thenReturn(listaPredios);
        var resultado = predioService.obterPredios();
        assertEquals(listaPredios, resultado);
    }

    @Test
    public void devePermitirDeletarPredio(){
        doNothing().when(predioRepository).deleteById(Mockito.any());

        var resultado = predioService.deletarPredio(mockPredio.getIdPredio());
        assertEquals(resultado, mockPredio.getIdPredio());
    }

    @Test
    public void devePermitirAtualizarPredio() throws Exception{
        var predioAtualizado = mockPredio;
        predioAtualizado.setNomePredio("Residencial das Flores");
        Mockito.when(predioRepository.save(Mockito.any())).thenReturn(predioAtualizado);
        Mockito.when(predioRepository.findById(Mockito.any())).thenReturn(Optional.of(mockPredio));

        var resultado = predioService.atualizarPredio(mockPredio.getIdPredio(), mockPredio);
        assertEquals(resultado, predioAtualizado);
    }

    @Test
    public void deveRetornarErroQuandoAtualizarPredioNomeNulo() throws Exception{
        var predioAtualizado = mockPredio;
        predioAtualizado.setNomePredio(null);

        assertThrows(ConstraintViolationException.class, () -> {
                    predioService.atualizarPredio(predioAtualizado.getIdPredio(), predioAtualizado);
                }
        );
    }




}

