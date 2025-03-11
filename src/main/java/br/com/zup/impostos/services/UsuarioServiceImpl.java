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

    @Override
    public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO){
        try {
            if (usuarioRepository.findByUserName(usuarioDTO.getUserName()).isPresent()){
                throw new UsuarioInvalidException("Username já existe!");
            }
            Usuario usuario = new Usuario();
            usuario.setUserName(usuarioDTO.getUserName());
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()));
            usuario.setRole(usuarioDTO.getRole());
            return usuarioRepository.save(usuario);
        } catch (UsuarioInvalidException e){
            System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String login(UsuarioLoginDTO usuarioLoginDTO){
        Usuario usuario = (Usuario) usuarioRepository.findByUserName(usuarioLoginDTO.getUserName())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado!") );
        if (!new BCryptPasswordEncoder().matches(usuarioLoginDTO.getPassword(), usuario.getPassword())){
            throw new UsuarioInvalidException("Senha inválida!");
        }
        List<String> roles = Collections.singletonList(usuario.getRole().name());
        System.out.println("Role do usuário " + usuario.getUserName() + ": " + roles);
        return jwtUtil.geradorToken(usuario.getUserName(), roles);
    }
}
