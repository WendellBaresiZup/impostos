package br.com.zup.impostos.services;

import br.com.zup.impostos.infra.JwtUtil;
import br.com.zup.impostos.models.LoginRequest;
import br.com.zup.impostos.models.Usuario;
import br.com.zup.impostos.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    private final JwtUtil jwtUtil;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Usuario cadastrarUsuario(Usuario usuario){
        if (usuarioRepository.findByUserName(usuario.getUserName()).isPresent()){
            throw new RuntimeException("Username já existe!");
        }
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public String login(LoginRequest loginRequest){
        Usuario usuario = (Usuario) usuarioRepository.findByUserName(loginRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!") );
        if (!new BCryptPasswordEncoder().matches(loginRequest.getPassword(), usuario.getPassword())){
            throw new RuntimeException("Senha inválida!");
        }
        return jwtUtil.geradorToken(usuario.getUserName());
    }
}
