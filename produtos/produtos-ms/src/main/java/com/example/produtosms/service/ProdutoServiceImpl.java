package com.example.produtosms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.produtosms.model.Produto;
import com.example.produtosms.repository.ProdutoRepository;
import com.example.produtosms.shared.ProdutoDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService{


    @Autowired
    private ProdutoRepository repo;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<ProdutoDto> obterTodos() {
        List<Produto> produtos = repo.findAll();

        return produtos.stream()
        .map(p -> mapper.map(p, ProdutoDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public ProdutoDto obterPorCodigo(int codigo) {
        Produto produto = repo.obterPorCodigo(codigo);

        return mapper.map(produto, ProdutoDto.class);
    }

    @Override
    public int obterQuantidadeEstoque(int codigo) {
        Produto produto = repo.obterPorCodigo(codigo);

        return produto.getQuantidadeEstoque();
    }

    @Override
    public Optional<ProdutoDto> obterPorId(String id) {
        Optional<Produto> produto = repo.findById(id);

        if (produto.isPresent()) {
            ProdutoDto dto = mapper.map(produto.get(), ProdutoDto.class);
            return Optional.of(dto);
        }

        return Optional.empty();
    }

    @Override
    public ProdutoDto inserirProduto(ProdutoDto produtoDto) {
        Produto produto = mapper.map(produtoDto, Produto.class);
        Produto produtoInserido = repo.insert(produto);

        return mapper.map(produtoInserido, ProdutoDto.class);
    }

    @Override
    public ProdutoDto atualizarProduto(String id, ProdutoDto produtoDto) {
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto.setId(id);
        Produto produtoAtualizado = repo.save(produto);

        return mapper.map(produtoAtualizado, ProdutoDto.class);
    }

    @Override
    public void removerProduto(String id) {
        repo.deleteById(id);
    }

}
