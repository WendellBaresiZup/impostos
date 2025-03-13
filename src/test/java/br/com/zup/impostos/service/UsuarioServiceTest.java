package br.com.zup.impostos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import br.com.zup.impostos.dto.Role;
import br.com.zup.impostos.dto.UsuarioDTO;
import br.com.zup.impostos.infra.JwtUtil;
import br.com.zup.impostos.models.Usuario;
import br.com.zup.impostos.repositories.UsuarioRepository;
import br.com.zup.impostos.services.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setUserName("testeUsuario");
        usuario.setPassword(new BCryptPasswordEncoder().encode("testePassword"));
        usuario.setRole(Role.ROLE_USER);
    }

    @Test
    public void cadastrarUsuarioTest(){
        when(usuarioRepository.findByUserName(anyString())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO usuarioDTO = new UsuarioDTO("testeUsuario", "testePassword", Role.ROLE_USER);
        Usuario result = usuarioService.cadastrarUsuario(usuarioDTO);

        assertNotNull(result);
        assertEquals("testeUsuario", result.getUserName());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

}

