package br.com.zup.impostos.dto;

import lombok.Data;

@Data
public class CalculoImpostoRequest {
    private Long tipoImpostoId;
    private double valorBase;

    public CalculoImpostoRequest(Long tipoImpostoId, double valorBase) {
        this.tipoImpostoId = tipoImpostoId;
        this.valorBase = valorBase;
    }

    public CalculoImpostoRequest() {
    }
}
