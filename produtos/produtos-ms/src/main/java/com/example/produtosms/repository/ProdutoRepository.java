package com.example.produtosms.repository;

import com.example.produtosms.model.Produto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String>{
    
    @Query("{'codigo': ?0}")
    Produto obterPorCodigo(int codigo);

    @Query("{'quantidadeEstoque': ?0}")
    int quantidadeEstoque(int codigo);
}
