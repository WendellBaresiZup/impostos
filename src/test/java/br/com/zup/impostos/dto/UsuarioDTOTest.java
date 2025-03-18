package br.com.zup.impostos.dto;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioDTOTest {

    private String userName;
    private String password;
    private Role role;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    public void setUp(){
        userName = "TesteUsuario";
        password = "password";
        role = Role.ROLE_USER;

        usuarioDTO = new UsuarioDTO(userName,password, role);
    }

    @Test
    public void usuarioDTOTeste(){
        assertEquals(userName, usuarioDTO.getUserName());
        assertEquals(password, usuarioDTO.getPassword());
        assertEquals(role, usuarioDTO.getRole());
    }
}
