package com.example.vendasms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.vendasms.service.VendaService;
import com.example.vendasms.shared.VendaDto;
import com.example.vendasms.view.model.VendaAlteracao;
import com.example.vendasms.view.model.VendaInclusao;
import com.example.vendasms.view.model.VendaModeloResponseDetalhes;
import com.example.vendasms.view.model.VendaResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendas")
public class Controller {
    
    @Autowired
    private VendaService service;

    ModelMapper mapper = new ModelMapper();

    @GetMapping
    public List<VendaResponse> obterTodos() {
        List<VendaDto> Vendasdto = service.obterTodos();

        return Vendasdto.stream()
        .map(v -> mapper.map(v, VendaResponse.class))
        .collect(Collectors.toList());
    }

    @GetMapping(value = "/{d1}/{d2}")
    public List<VendaResponse> VendasPorPeriodo(@PathVariable String d1, @PathVariable String d2) {
        List<VendaDto> dto = service.obterVendasPorPeriodo(d1, d2);

        return dto.stream()
        .map(d -> mapper.map(d, VendaResponse.class))
        .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaModeloResponseDetalhes> obterPorId(@PathVariable String id) {
        Optional<VendaDto> vendaDto = service.obterPorId(id);

        if (vendaDto.isPresent()) {
            VendaModeloResponseDetalhes response = mapper.map(vendaDto.get(), VendaModeloResponseDetalhes.class);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<VendaResponse> realizarVenda(@RequestBody @Valid VendaInclusao inclusao) {
        VendaDto vendaDto = mapper.map(inclusao, VendaDto.class);
        VendaDto vendaRealizada = service.realizarVenda(vendaDto);

        if (vendaRealizada == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(mapper.map(vendaRealizada, VendaResponse.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VendaResponse> atualizarVenda(@PathVariable String id, @RequestBody VendaAlteracao alteracao) {
        VendaDto vendaDto = mapper.map(alteracao, VendaDto.class);
        VendaDto vendaAlterada = service.atualizarVenda(id, vendaDto);

        return new ResponseEntity<>(mapper.map(vendaAlterada, VendaResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> excluirVenda(@PathVariable String id) {
        service.deletarVenda(id);

        return new ResponseEntity<>("Venda Deletada com sucesso!", HttpStatus.OK);
    }
}

