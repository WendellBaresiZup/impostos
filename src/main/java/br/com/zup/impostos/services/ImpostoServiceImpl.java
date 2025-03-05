package br.com.zup.impostos.services;

import br.com.zup.impostos.models.Imposto;
import br.com.zup.impostos.repositories.ImpostoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpostoService {

    private ImpostoRepository impostoRepository;

    public ImpostoService(ImpostoRepository impostoRepository) {
        this.impostoRepository = impostoRepository;
    }

    public Double calcularImposto(Imposto imposto, Double valorBase){
        return valorBase * imposto.getAliquota() / 100;
    }

    public List<Imposto> todosImpostos(){
        return impostoRepository.findAll();
    }
}
