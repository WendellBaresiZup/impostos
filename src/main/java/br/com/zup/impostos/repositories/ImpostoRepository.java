package br.com.zup.impostos.repositories;

import br.com.zup.impostos.models.Imposto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpostoRepository extends JpaRepository<Imposto, Long> {
}
