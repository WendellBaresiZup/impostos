package br.com.zup.impostos.dto;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioLoginDTOTest {

    private String userName;
    private String password;
    private UsuarioLoginDTO usuarioLoginDTO;

    @BeforeEach
    public void setUp(){
        userName = "TesteUsuario";
        password = "password";

        usuarioLoginDTO = new UsuarioLoginDTO(userName,password);
    }

    @Test
    public void usarioLoginDTOTeste(){
        assertEquals("TesteUsuario", usuarioLoginDTO.getUserName());
        assertEquals("password", usuarioLoginDTO.getPassword());
    }




    @Data
    public class UsuarioLoginDTO {
        private String userName;
        private String password;

        public UsuarioLoginDTO(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public UsuarioLoginDTO(){
        }

    }
}
