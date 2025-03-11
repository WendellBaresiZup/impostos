package br.com.zup.impostos.services;

import br.com.zup.impostos.dto.UsuarioDTO;
import br.com.zup.impostos.dto.UsuarioLoginDTO;
import br.com.zup.impostos.exceptions.UsuarioInvalidException;
import br.com.zup.impostos.exceptions.UsuarioNotFoundException;
import br.com.zup.impostos.infra.JwtUtil;
import br.com.zup.impostos.models.Usuario;
import br.com.zup.impostos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private Map<String, String> roleCache = new HashMap<>();


    @Override
    public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO){
        if (usuarioRepository.findByUserName(usuarioDTO.getUserName()).isPresent()){
            throw new UsuarioInvalidException("Username já existe!");
        }
        Usuario usuario = new Usuario();
        usuario.setUserName(usuarioDTO.getUserName());
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public String login(UsuarioLoginDTO usuarioLoginDTO){
        Usuario usuario = (Usuario) usuarioRepository.findByUserName(usuarioLoginDTO.getUserName())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado!") );
        if (!new BCryptPasswordEncoder().matches(usuarioLoginDTO.getPassword(), usuario.getPassword())){
            throw new UsuarioInvalidException("Senha inválida!");
        }
        String role = roleCache.get(usuarioLoginDTO.getUserName());
        List<String> roles = Arrays.asList(role);
        return jwtUtil.geradorToken(usuario.getUserName(), roles);
    }
}
