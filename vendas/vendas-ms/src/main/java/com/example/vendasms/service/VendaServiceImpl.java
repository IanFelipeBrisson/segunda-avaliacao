package com.example.vendasms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.vendasms.clientehttp.ProdutosFeignClient;
import com.example.vendasms.model.Venda;
import com.example.vendasms.repository.VendaRepository;
import com.example.vendasms.shared.VendaDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaServiceImpl implements VendaService{


    @Autowired
    private VendaRepository repo;

    @Autowired
    private ProdutosFeignClient produtoFeignClients;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<VendaDto> obterTodos() {
       List<Venda> vendas = repo.findAll();

       return vendas.stream()
       .map(v -> mapper.map(v, VendaDto.class))
       .collect(Collectors.toList());
    }

    @Override
    public List<VendaDto> obterVendasPorPeriodo(String d1, String d2) {
        List<Venda> vendas = repo.vendasPorPeriodo(d1, d2);

        return vendas.stream()
        .map(v -> mapper.map(v, VendaDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<VendaDto> obterPorId(String id) {
        Optional<Venda> venda = repo.findById(id);

        if (venda.isPresent()) {
            VendaDto dto = mapper.map(venda.get(), VendaDto.class);
            dto.setProdutoVendido(produtoFeignClients.obterProdutos(dto.getCodigoProduto()));
            return Optional.of(dto);
        }

        return Optional.empty();
    }

    @Override
    public VendaDto realizarVenda(VendaDto vendaDto) {
        Venda venda = mapper.map(vendaDto, Venda.class);

        if (venda.getQuantidadeVendida() <= produtoFeignClients.obterEstoque(venda.getCodigoProduto())) {
            Venda vendaRealizada = repo.insert(venda);
            return mapper.map(vendaRealizada, VendaDto.class);
        }
        
        return null;

    }

    @Override
    public VendaDto atualizarVenda(String id, VendaDto vendaDto) {
        Venda venda = mapper.map(vendaDto, Venda.class);
        venda.setId(id);
        Venda vendaAtualizada = repo.save(venda);

        return mapper.map(vendaAtualizada, VendaDto.class);
    }

    @Override
    public void deletarVenda(String id) {
        repo.deleteById(id);
    }

}
