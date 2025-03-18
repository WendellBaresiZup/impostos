package br.com.zup.impostos.dto;

import lombok.Data;

@Data
public class CalculoImpostoRequestDTO {
    private Long tipoImpostoId;
    private double valorBase;

    public CalculoImpostoRequestDTO(Long tipoImpostoId, double valorBase) {
        this.tipoImpostoId = tipoImpostoId;
        this.valorBase = valorBase;
    }

    public CalculoImpostoRequestDTO() {
    }
}
