package com.example.produtosms.view.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProdutoInclusao {
    @Min(value = 1, message = "O Código não pode ser negativo ou zero!!")
    private int codigo;
    @NotBlank(message = "O nome não pode ser vazio!!")
    private String nome;
    @Positive(message = "O valor não pode ser negativo ou zero!!")
    private double valor;
    @Min(value = 1,message = "O estoque não pode ser negativo ou zero!!")
    private int quantidadeEstoque;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}