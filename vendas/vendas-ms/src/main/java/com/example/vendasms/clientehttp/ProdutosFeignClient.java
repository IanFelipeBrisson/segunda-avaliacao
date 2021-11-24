package com.example.vendasms.clientehttp;

import com.example.vendasms.shared.Produto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produtos-ms", fallback = ProdutosFeignClientFallback.class)
public interface ProdutosFeignClient {
    
    @GetMapping(path = "/api/produtos/obterPorCodigo/{codigo}")
    public Produto obterProdutos(@PathVariable int codigo);

    @GetMapping(path = "/api/produtos/obterEstoque/{codigo}")
    public int obterEstoque(@PathVariable int codigo);
}

@Component
class ProdutosFeignClientFallback implements ProdutosFeignClient{

    @Override
    public Produto obterProdutos(int codigo) {
        return null;
    }

    @Override
    public int obterEstoque(int codigo) {
        return 0;
    }

}

