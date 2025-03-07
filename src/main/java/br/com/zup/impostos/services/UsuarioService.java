package br.com.zup.impostos.services;

import br.com.zup.impostos.dto.UsuarioLoginDTO;
import br.com.zup.impostos.models.Usuario;

public interface UsuarioService {
    Usuario cadastrarUsuario(Usuario usuario);
    String login(UsuarioLoginDTO usuarioLoginDTO);
}
