package br.com.zup.impostos.dto;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImpostoDTOTest {

    private String nome;
    private String descricao;
    private double aliquota;
    private ImpostoDTO impostoDTO;

    @BeforeEach
    public void setUp(){

        nome = "IR";
        descricao = "Imposto sobre a renda de pessoas físicas";
        aliquota = 27.5;

        impostoDTO = new ImpostoDTO(nome, descricao, aliquota);
    }

    @Test
    public void impostoDTOTeste(){
        assertEquals(nome, impostoDTO.getNome());
        assertEquals(descricao, impostoDTO.getDescricao());
        assertEquals(aliquota, impostoDTO.getAliquota());
    }

}
