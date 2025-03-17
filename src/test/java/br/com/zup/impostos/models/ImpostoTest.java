package br.com.zup.impostos.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
