package br.com.zup.impostos.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ImpostoTest {

    @Test
    public void impostoTeste(){
        Imposto imposto = new Imposto(1L, "IR", "Imposto sobre a renda de pessoas físicas",27.5);

        assertEquals(1L,imposto.getId());
        assertEquals("IR", imposto.getNome());
        assertEquals("Imposto sobre a renda de pessoas físicas", imposto.getDescricao());
        assertEquals(27.5, imposto.getAliquota());
    }

}
