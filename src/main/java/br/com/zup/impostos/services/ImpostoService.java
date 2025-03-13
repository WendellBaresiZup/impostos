package br.com.zup.impostos.services;

import br.com.zup.impostos.dto.CalculoImpostoResponse;
import br.com.zup.impostos.dto.ImpostoDTO;
import br.com.zup.impostos.models.Imposto;

import java.util.List;

public interface ImpostoService {
    Double calcularImposto(Long impostoId, double valorBase);
    List<Imposto> listarTodosImpostos();
    Imposto listarImpostoPeloId(Long id);
    Imposto cadastrarImposto(ImpostoDTO impostoDTO);
    void deletarImpostoPeloId(Long id);
    CalculoImpostoResponse calcularImpostoResponse(Long impostoId, double valorBase);
}
