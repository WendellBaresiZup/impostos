package br.com.zup.impostos.services;

import br.com.zup.impostos.models.Imposto;
import br.com.zup.impostos.repositories.ImpostoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpostoServiceImpl implements ImpostoService{

    private ImpostoRepository impostoRepository;

    public ImpostoServiceImpl(ImpostoRepository impostoRepository) {
        this.impostoRepository = impostoRepository;
    }


    @Override
    public Double calcularImposto(Imposto imposto, double valorBase) {
        return valorBase * imposto.getAliquota() / 100;
    }

    @Override
    public List<Imposto> todosImpostos(){
        return impostoRepository.findAll();
    }

    @Override
    public Imposto salvarImposto(Imposto imposto){
        return impostoRepository.save(imposto);
    }
}
