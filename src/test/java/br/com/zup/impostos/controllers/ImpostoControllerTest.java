package br.com.zup.impostos.controllers;


import br.com.zup.impostos.infra.JwtUtil;
import br.com.zup.impostos.models.Imposto;

import br.com.zup.impostos.services.ImpostoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.ResourceBundle;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImpostoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ImpostoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImpostoService impostoService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    private Imposto imposto;

    @BeforeEach
    public void setUp() {
        imposto = new Imposto(1L, "IR", "Imposto sobre a renda", 27.5);

        when(impostoService.listarTodosImpostos()).thenReturn(List.of(imposto));
    }


    @Test
    public void listarTodosImpostosTeste() throws Exception{
        mockMvc.perform(get("/api/tipos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()) // Validate array size
                .andExpect(jsonPath("$[0].id").value(1)) // Validate object fields
                .andExpect(jsonPath("$[0].nome").value("IR"))
                .andExpect(jsonPath("$[0].descricao").value("Imposto sobre a renda"))
                .andExpect(jsonPath("$[0].aliquota").value(27.5));
    }

    @Test
    public void listarImpostoPeloIdTeste() throws Exception {
        Long id = 1L;

        when(impostoService.listarImpostoPeloId(id)).thenReturn(imposto);

        mockMvc.perform(get("/api/tipos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("IR"))
                .andExpect(jsonPath("$.descricao").value("Imposto sobre a renda"))
                .andExpect(jsonPath("$.aliquota").value(27.5));
    }

}
