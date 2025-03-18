package br.com.zup.impostos.controllers;


import br.com.zup.impostos.dto.CalculoImpostoResponseDTO;
import br.com.zup.impostos.dto.ImpostoDTO;
import br.com.zup.impostos.infra.JwtUtil;
import br.com.zup.impostos.models.Imposto;
import br.com.zup.impostos.services.ImpostoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;


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
    private ImpostoDTO impostoDTO;

    @BeforeEach
    public void setUp() {
        imposto = new Imposto(1L, "IR", "Imposto sobre a renda", 27.5);
        impostoDTO = new ImpostoDTO("IR", "Imposto sobre a renda", 27.5);
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

    @Test
    public void cadastrarImpostoTeste() throws Exception{
        when(impostoService.cadastrarImposto(any(ImpostoDTO.class))).thenReturn(imposto);

        String impostoDTOJson = """
                {
                      "nome": "IR",
                      "descricao": "Imposto sobre a renda",
                      "aliquota": 27.5
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tipos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(impostoDTOJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("IR"))
                .andExpect(jsonPath("$.descricao").value("Imposto sobre a renda"))
                .andExpect(jsonPath("$.aliquota").value(27.5));
    }

    @Test
    public void deletarImpostoPeloIdTeste() throws Exception{
        Long id = 1L;
        doNothing().when(impostoService).deletarImpostoPeloId(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tipos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void calcularImpostoTeste() throws Exception{
        Long tipoImpostoId = 1L;
        double valorBase = 1000.0;

        CalculoImpostoResponseDTO calculoImpostoResponseDTO = new CalculoImpostoResponseDTO
                (imposto.getNome(), valorBase, imposto.getAliquota(), 275.0);
        when(impostoService.calcularImpostoResponse(tipoImpostoId, valorBase)).thenReturn(calculoImpostoResponseDTO);

        String calculoImpostoDTOJson = """
                {
                      "tipoImpostoId": 1,
                      "valorBase": 1000.0
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tipos/calculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(calculoImpostoDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoImposto").value("IR"))
                .andExpect(jsonPath("$.valorBase").value(1000.0))
                .andExpect(jsonPath("$.aliquota").value(27.5))
                .andExpect(jsonPath("$.valorImposto").value(275.0));

    }

}
