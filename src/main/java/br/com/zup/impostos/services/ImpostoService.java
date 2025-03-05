package br.com.zup.impostos.services;

import br.com.zup.impostos.models.Imposto;
import org.springframework.stereotype.Service;

@Service
public class ImpostoService {


    public Double calcularImposto(Imposto imposto, Double valorBase){
        return valorBase * imposto.getAliquota() / 100;
    }
}
