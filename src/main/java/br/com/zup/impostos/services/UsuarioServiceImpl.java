package br.com.zup.impostos.services;

import br.com.zup.impostos.dto.UsuarioDTO;
import br.com.zup.impostos.dto.UsuarioLoginDTO;
import br.com.zup.impostos.infra.JwtUtil;
import br.com.zup.impostos.models.Usuario;
import br.com.zup.impostos.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    private final JwtUtil jwtUtil;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO){
        if (usuarioRepository.findByUserName(usuarioDTO.getUserName()).isPresent()){
            throw new RuntimeException("Username já existe!");
        }
        Usuario usuario = new Usuario();
        usuario.setUserName(usuarioDTO.getUserName());
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()));
        usuario.setRole(usuarioDTO.getRole());
        return usuarioRepository.save(usuario);
    }

    @Override
    public String login(UsuarioLoginDTO usuarioLoginDTO){
        Usuario usuario = (Usuario) usuarioRepository.findByUserName(usuarioLoginDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!") );
        if (!new BCryptPasswordEncoder().matches(usuarioLoginDTO.getPassword(), usuario.getPassword())){
            throw new RuntimeException("Senha inválida!");
        }
        return jwtUtil.geradorToken(usuario.getUserName());
    }
}
