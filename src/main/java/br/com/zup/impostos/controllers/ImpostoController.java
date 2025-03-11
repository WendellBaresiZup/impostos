package br.com.zup.impostos.controllers;

import br.com.zup.impostos.dto.ImpostoDTO;
import br.com.zup.impostos.models.Imposto;
import br.com.zup.impostos.services.ImpostoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class ImpostoController {
    private final ImpostoService impostoService;

    public ImpostoController(ImpostoService impostoService) {
        this.impostoService = impostoService;
    }

    @GetMapping
    public ResponseEntity<List<Imposto>> listarTodosImpostos(){
        List<Imposto> impostos = impostoService.listarTodosImpostos();
        return new ResponseEntity<>(impostos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imposto> listarImpostoPeloId(@PathVariable Long id){
        Imposto imposto = impostoService.listarImpostoPeloId(id);
        return new ResponseEntity<>(imposto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Imposto> cadastrarImposto(@RequestBody ImpostoDTO impostoDTO){
        Imposto novoImposto = impostoService.cadastrarImposto(impostoDTO);
        return new ResponseEntity<>(novoImposto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarImpostoPeloId(@PathVariable Long id){
        impostoService.deletarImpostoPeloId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
