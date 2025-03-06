package br.com.zup.impostos.service;

import br.com.zup.impostos.repositories.UsuarioRepository;
import br.com.zup.impostos.services.UsuarioServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;
}
