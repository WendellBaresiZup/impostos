package br.com.zup.impostos.repositories;

import br.com.zup.impostos.models.Imposto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpostoRepository extends JpaRepository<Imposto, Long> {
}
