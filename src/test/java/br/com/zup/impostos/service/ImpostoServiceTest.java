package br.com.zup.impostos.service;

import br.com.zup.impostos.dto.CalculoImpostoResponse;
import br.com.zup.impostos.dto.ImpostoDTO;
import br.com.zup.impostos.models.Imposto;
import br.com.zup.impostos.repositories.ImpostoRepository;
import br.com.zup.impostos.services.ImpostoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImpostoServiceTest {

    @Mock
    private ImpostoRepository impostoRepository;

    @InjectMocks
    private ImpostoServiceImpl impostoService;

    private Imposto imposto;
    private ImpostoDTO impostoDTO;

    @BeforeEach
    public void setUp(){
        imposto = new Imposto(1L, "IR", "Imposto sobre a renda de pessoas físicas", 27.5);
        impostoDTO = new ImpostoDTO("IR", "Imposto sobre a renda de pessoas físicas", 27.5);
    }

    @Test
    public void calcularImpostoTeste(){
        when(impostoRepository.findById(anyLong())).thenReturn(Optional.of(imposto));
        double valorBase = 100.0;
        double valorImposto = impostoService.calcularImposto(1L, valorBase);

        assertEquals(27.5, valorImposto);
    }


    @Test
    public void listarTodosImpostosTeste(){
        when(impostoRepository.findAll()).thenReturn(Arrays.asList(imposto));

        List<Imposto> impostos = impostoService.listarTodosImpostos();

        assertEquals(1, impostos.size());
        assertEquals("IR", impostos.get(0).getNome());
    }

    @Test
    public void listraImpostoPeloIdTeste(){
        when(impostoRepository.findById(anyLong())).thenReturn(Optional.of(imposto));

        Imposto resultado = impostoService.listarImpostoPeloId(1L);

        assertEquals(imposto.getId(), resultado.getId());
        assertEquals(imposto.getNome(), resultado.getNome());
        assertEquals(imposto.getDescricao(), resultado.getDescricao());
        assertEquals(imposto.getAliquota(), resultado.getAliquota());
    }

    @Test
    public void listraImpostoPeloId_Error(){
        when(impostoRepository.findById(anyLong())).thenReturn(Optional.empty());

        Imposto resultado = impostoService.listarImpostoPeloId(2L);

        assertNull(resultado);
    }

    @Test
    public void cadastrarImpostoTeste(){
        when(impostoRepository.save(any(Imposto.class))).thenReturn(imposto);

        Imposto resultado = impostoService.cadastrarImposto(impostoDTO);

        assertEquals(imposto.getId(), resultado.getId());
        assertEquals(imposto.getNome(), resultado.getNome());
        assertEquals(imposto.getDescricao(), resultado.getDescricao());
        assertEquals(imposto.getAliquota(), resultado.getAliquota());

        verify(impostoRepository, times(1)).save(any(Imposto.class));
    }

    @Test
    public void deletarImpostoPeloIdTeste(){
        doNothing().when(impostoRepository).deleteById(anyLong());

        impostoService.deletarImpostoPeloId(1L);

        verify(impostoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void calcularImpostoResponseTeste(){
        when(impostoRepository.findById(anyLong())).thenReturn(Optional.of(imposto));

        double valorBase = 1000.0;
        CalculoImpostoResponse resposta = impostoService.calcularImpostoResponse(1L, valorBase);

        assertEquals("IR", resposta.getTipoImposto());
        assertEquals(1000.0, resposta.getValorBase());
        assertEquals(27.5, resposta.getAliquota());
        assertEquals(275.0, resposta.getValorImposto());
    }


}
