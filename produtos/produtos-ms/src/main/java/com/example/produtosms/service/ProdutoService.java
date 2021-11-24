package com.example.produtosms.service;

import java.util.List;
import java.util.Optional;

import com.example.produtosms.shared.ProdutoDto;

public interface ProdutoService {
    List<ProdutoDto> obterTodos();
    ProdutoDto obterPorCodigo(int codigo);
    int obterQuantidadeEstoque(int codigo);
    Optional<ProdutoDto> obterPorId(String id);
    ProdutoDto inserirProduto(ProdutoDto produtoDto);
    ProdutoDto atualizarProduto(String id, ProdutoDto produtoDto);
    void removerProduto(String id);
}
