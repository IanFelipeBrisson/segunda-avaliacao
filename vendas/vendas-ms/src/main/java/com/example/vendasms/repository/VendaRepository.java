package com.example.vendasms.repository;

import java.util.List;

import com.example.vendasms.model.Venda;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends MongoRepository<Venda, String>{
    
    @Query("{'data': {'$gte': ?0, '$lte': ?1}}")
    List<Venda> vendasPorPeriodo(String d1, String d2);
}
