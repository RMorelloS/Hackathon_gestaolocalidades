package com.fiap.postech.gestaolocalidades.service;


import com.fiap.postech.gestaolocalidades.config.ClassGenerator;
import com.fiap.postech.gestaolocalidades.model.Localidade;
import com.fiap.postech.gestaolocalidades.repository.LocalidadeRepository;
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

@WebMvcTest(controllers=LocalidadeService.class)
public class LocalidadeServiceTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalidadeRepository localidadeRepository;


    @Autowired
    private LocalidadeService localidadeService;


    private Localidade mockLocalidade;

    public LocalidadeServiceTest(){
        mockLocalidade = ClassGenerator.geraLocalidade();
    }

    @Test
    public void devePermitirCadastrarLocalidade(){
        Mockito.when(localidadeRepository.save(Mockito.any())).thenReturn(mockLocalidade);

        var resultado = localidadeService.cadastrarLocalidade(mockLocalidade);
        assertEquals(mockLocalidade.getIdLocalidade(), resultado.getIdLocalidade());
    }

    @Test
    public void deveRetornarErroQuandoCadastrarLocalidadeEnderecoNulo(){
        var localidadeEnderecoNulo = mockLocalidade;
        localidadeEnderecoNulo.setNomeLocalidade(null);
        assertThrows(ConstraintViolationException.class, () -> {
            localidadeService.cadastrarLocalidade(localidadeEnderecoNulo);
        });

    }


    @Test
    public void devePermitirObterLocalidades(){
        List<Localidade> listaLocalidades = new ArrayList<>();
        Mockito.when(localidadeRepository.findAll()).thenReturn(listaLocalidades);
        var resultado = localidadeService.obterLocalidades();
        assertEquals(listaLocalidades, resultado);
    }

    @Test
    public void devePermitirDeletarLocalidade(){
        doNothing().when(localidadeRepository).deleteById(Mockito.any());

        var resultado = localidadeService.deletarLocalidade(mockLocalidade.getIdLocalidade());
        assertEquals(resultado, mockLocalidade.getIdLocalidade());
    }

    @Test
    public void devePermitirAtualizarLocalidade() throws Exception{
        var localidadeAtualizado = mockLocalidade;
        localidadeAtualizado.setNomeLocalidade("Chácara das Flores");
        Mockito.when(localidadeRepository.save(Mockito.any())).thenReturn(localidadeAtualizado);
        Mockito.when(localidadeRepository.findById(Mockito.any())).thenReturn(Optional.of(mockLocalidade));

        var resultado = localidadeService.atualizarLocalidade(mockLocalidade.getIdLocalidade(), mockLocalidade);
        assertEquals(resultado, localidadeAtualizado);
    }

    @Test
    public void deveRetornarErroQuandoAtualizarLocalidadeEnderecoNulo() throws Exception{
        var localidadeAtualizado = mockLocalidade;
        localidadeAtualizado.setNomeLocalidade(null);

        assertThrows(ConstraintViolationException.class, () -> {
                    localidadeService.atualizarLocalidade(localidadeAtualizado.getIdLocalidade(), localidadeAtualizado);
                }
        );
    }




}

