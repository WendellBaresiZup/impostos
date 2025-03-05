package br.com.zup.impostos.services;

import br.com.zup.impostos.models.Imposto;
import br.com.zup.impostos.repositories.ImpostoRepository;

import java.util.List;

public interface ImpostoService {
    Double calcularImposto(Imposto imposto, double valorBase);
    List<Imposto> todosImpostos();

}
