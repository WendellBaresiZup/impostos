package br.com.zup.impostos.controllers;

import br.com.zup.impostos.models.Usuario;
import br.com.zup.impostos.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api//usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
    }
}
