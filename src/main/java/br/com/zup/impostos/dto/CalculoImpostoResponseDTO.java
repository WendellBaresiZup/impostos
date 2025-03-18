package br.com.zup.impostos.dto;

import lombok.Data;

@Data
public class CalculoImpostoResponse {
    private String tipoImposto;
    private double valorBase;
    private double aliquota;
    private double valorImposto;

    public CalculoImpostoResponse(String tipoImposto, double valorBase, double aliquota, double valorImposto) {
        this.tipoImposto = tipoImposto;
        this.valorBase = valorBase;
        this.aliquota = aliquota;
        this.valorImposto = valorImposto;
    }

    public CalculoImpostoResponse(){

    }
}
