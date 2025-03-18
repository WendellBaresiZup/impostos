package br.com.zup.impostos.services;

import br.com.zup.impostos.dto.CalculoImpostoResponseDTO;
import br.com.zup.impostos.dto.ImpostoDTO;
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
    public Double calcularImposto(Long impostoId, double valorBase) {
        Imposto imposto = listarImpostoPeloId(impostoId);
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
    public Imposto cadastrarImposto(ImpostoDTO impostoDTO){
        Imposto imposto = new Imposto();
        imposto.setNome(impostoDTO.getNome());
        imposto.setDescricao(impostoDTO.getDescricao());
        imposto.setAliquota(impostoDTO.getAliquota());
        return impostoRepository.save(imposto);
    }

    @Override
    public void deletarImpostoPeloId(Long id){
        impostoRepository.deleteById(id);
    }

    @Override
    public CalculoImpostoResponseDTO calcularImpostoResponse(Long impostoId, double valorBase) {
        Imposto imposto = listarImpostoPeloId(impostoId);
        double valorImposto = valorBase * imposto.getAliquota() / 100;

        CalculoImpostoResponseDTO calculoImpostoResponseDTO = new CalculoImpostoResponseDTO();
        calculoImpostoResponseDTO.setTipoImposto(imposto.getNome());
        calculoImpostoResponseDTO.setValorBase(valorBase);
        calculoImpostoResponseDTO.setAliquota(imposto.getAliquota());
        calculoImpostoResponseDTO.setValorImposto(valorImposto);
        return calculoImpostoResponseDTO;
    }
}
