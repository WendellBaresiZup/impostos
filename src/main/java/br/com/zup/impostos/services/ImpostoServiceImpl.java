package br.com.zup.impostos.services;

import br.com.zup.impostos.infra.JwtUtil;
import br.com.zup.impostos.models.Imposto;
import br.com.zup.impostos.repositories.ImpostoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpostoServiceImpl implements ImpostoService{

    private final ImpostoRepository impostoRepository;

    private final JwtUtil jwtUtil;

    public ImpostoServiceImpl(ImpostoRepository impostoRepository, JwtUtil jwtUtil) {
        this.impostoRepository = impostoRepository;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Double calcularImposto(Imposto imposto, double valorBase) {
        return valorBase * imposto.getAliquota() / 100;
    }

    @Override
    public List<Imposto> listarTodosImpostos(){
        return impostoRepository.findAll();
    }

    @Override
    public Imposto listarImpostoPeloId(Long id){
        return impostoRepository.findById(id).orElse(null);
    }

    @Override
    public Imposto salvarImposto(Imposto imposto){
        return impostoRepository.save(imposto);
    }

    @Override
    public void deletarImpostoPeloId(Long id){
        impostoRepository.deleteById(id);
    }
}
