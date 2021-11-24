package com.example.vendasms.view.model;

public class VendaAlteracao {
    private int codigoProduto;
    private String data;
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
