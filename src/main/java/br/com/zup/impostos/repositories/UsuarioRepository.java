package br.com.zup.impostos.repositories;

import br.com.zup.impostos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Object> findByUserName(String userName);
}
