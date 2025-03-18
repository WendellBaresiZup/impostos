package br.com.zup.impostos.controllers;

import br.com.zup.impostos.dto.Role;
import br.com.zup.impostos.dto.UsuarioDTO;
import br.com.zup.impostos.dto.UsuarioLoginDTO;
import br.com.zup.impostos.infra.JwtUtil;
import br.com.zup.impostos.models.Usuario;
import br.com.zup.impostos.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDetailsService userDetailsService;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    public void setUp(){
        usuario = new Usuario("1", "TesteUsuario", "TestePassword", Role.ROLE_USER);
        usuarioDTO = new UsuarioDTO("TesteUsuario", "TestePassword", Role.ROLE_USER);
    }

    @Test
    public void cadastrarUsuarioTeste() throws Exception{
        when(usuarioService.cadastrarUsuario(any(UsuarioDTO.class))).thenReturn(usuario);

        String usuarioJson = """
                {
                      "userName": "TesteUsuario",
                      "password": "TestePassword",
                      "role": "ROLE_USER"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andDo(result -> System.out.println("Response: " + result.getResponse().getContentAsString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").value("1"))
                .andExpect(jsonPath("$.userName").value("TesteUsuario"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }

    @Test
    public void loginTeste() throws Exception{
        String token = "mocked-jwt-token";
        when(usuarioService.login(any(UsuarioLoginDTO.class))).thenReturn(token);

        String loginUsuarioJson = """
                {
                      "userName": "TesteUsuario",
                      "password": "TestePassword"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuario/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginUsuarioJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token));

    }

}
