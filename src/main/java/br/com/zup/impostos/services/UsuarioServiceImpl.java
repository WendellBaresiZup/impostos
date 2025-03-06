package br.com.zup.impostos.services;

import br.com.zup.impostos.repositories.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

}
