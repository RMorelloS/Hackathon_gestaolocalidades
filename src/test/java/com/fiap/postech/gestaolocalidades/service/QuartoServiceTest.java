package com.fiap.postech.gestaolocalidades.service;


import com.fiap.postech.gestaolocalidades.config.ClassGenerator;
import com.fiap.postech.gestaolocalidades.model.Quarto;
import com.fiap.postech.gestaolocalidades.repository.QuartoRepository;
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

@WebMvcTest(controllers=QuartoService.class)
public class QuartoServiceTest {


    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private QuartoRepository QuartoRepository;


    @Autowired
    private QuartoService QuartoService;


    private Quarto mockQuarto;

    public QuartoServiceTest(){
        mockQuarto = ClassGenerator.geraQuarto();
    }

    @Test
    public void devePermitirCadastrarQuarto(){
        Mockito.when(QuartoRepository.save(Mockito.any())).thenReturn(mockQuarto);

        var resultado = QuartoService.cadastrarQuarto(mockQuarto);
        assertEquals(mockQuarto.getIdQuarto(), resultado.getIdQuarto());
    }

    @Test
    public void deveRetornarErroQuandoCadastrarQuartoTipoNulo(){
        var QuartoNomeNulo = mockQuarto;
        QuartoNomeNulo.setTipoQuarto(null);
        assertThrows(ConstraintViolationException.class, () -> {
            QuartoService.cadastrarQuarto(QuartoNomeNulo);
        });

    }


    @Test
    public void devePermitirObterQuartos(){
        List<Quarto> listaQuartos = new ArrayList<>();
        Mockito.when(QuartoRepository.findAll()).thenReturn(listaQuartos);
        var resultado = QuartoService.obterQuartos();
        assertEquals(listaQuartos, resultado);
    }

    @Test
    public void devePermitirDeletarQuarto(){
        doNothing().when(QuartoRepository).deleteById(Mockito.any());

        var resultado = QuartoService.deletarQuarto(mockQuarto.getIdQuarto());
        assertEquals(resultado, mockQuarto.getIdQuarto());
    }

    @Test
    public void devePermitirAtualizarQuarto() throws Exception{
        var QuartoAtualizado = mockQuarto;
        QuartoAtualizado.setTipoQuarto("Single");
        Mockito.when(QuartoRepository.save(Mockito.any())).thenReturn(QuartoAtualizado);
        Mockito.when(QuartoRepository.findById(Mockito.any())).thenReturn(Optional.of(mockQuarto));

        var resultado = QuartoService.atualizarQuarto(mockQuarto.getIdQuarto(), mockQuarto);
        assertEquals(resultado, QuartoAtualizado);
    }

    @Test
    public void deveRetornarErroQuandoAtualizarQuartoTipoNulo() throws Exception{
        var QuartoAtualizado = mockQuarto;
        QuartoAtualizado.setTipoQuarto(null);

        assertThrows(ConstraintViolationException.class, () -> {
                    QuartoService.atualizarQuarto(QuartoAtualizado.getIdQuarto(), QuartoAtualizado);
                }
        );
    }




}

