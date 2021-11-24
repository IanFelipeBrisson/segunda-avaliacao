package com.example.vendasms.service;

import java.util.List;
import java.util.Optional;

import com.example.vendasms.shared.VendaDto;

public interface VendaService {
    List<VendaDto> obterTodos();
    List<VendaDto> obterVendasPorPeriodo(String d1, String d2);
    Optional<VendaDto> obterPorId(String id);
    VendaDto realizarVenda(VendaDto vendaDto);
    VendaDto atualizarVenda(String id, VendaDto vendaDto);
    void deletarVenda(String id);
}