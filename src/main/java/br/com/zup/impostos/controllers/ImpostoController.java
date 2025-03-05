package br.com.zup.impostos.controllers;

import br.com.zup.impostos.models.Imposto;
import br.com.zup.impostos.services.ImpostoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Imposto> impostos = impostoService.todosImpostos();
        return new ResponseEntity<>(impostos, HttpStatus.OK);
    }
}
