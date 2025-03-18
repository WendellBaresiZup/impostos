package br.com.zup.impostos.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioResponseDTOTest {

    private Long id;
    private String userName;
    private Role role;
    private UsuarioResponseDTO usuarioResponseDTO;

    @BeforeEach
    public void setUp(){
        id = 1L;
        userName = "testeUsuario";
        role = Role.ROLE_USER;

        usuarioResponseDTO = new UsuarioResponseDTO(id, userName, role);
    }

    @Test
    public void usuarioResponseDTOTeste(){
        assertEquals(1L, usuarioResponseDTO.getId());
        assertEquals("testeUsuario", usuarioResponseDTO.getUserName());
        assertEquals(role, usuarioResponseDTO.getRole());
    }
}
