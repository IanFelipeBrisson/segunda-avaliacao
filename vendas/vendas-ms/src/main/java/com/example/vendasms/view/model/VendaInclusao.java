package com.example.vendasms.view.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class VendaInclusao {
    @Min(value = 1, message = "O codigo do produto não pode ser 0 ou negativo")
    private int codigoProduto;
    @NotBlank(message = "Data inválida")
    private String data;
    @Min(value = 1, message = "A quantidadeVendida não pode ser 0 ou negativo")
    private int quantidadeVendida;

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    
}
