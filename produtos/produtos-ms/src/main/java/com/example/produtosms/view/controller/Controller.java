package com.example.produtosms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.produtosms.service.ProdutoService;
import com.example.produtosms.shared.ProdutoDto;
import com.example.produtosms.view.model.ProdutoAlteracao;
import com.example.produtosms.view.model.ProdutoInclusao;
import com.example.produtosms.view.model.ProdutoResponse;

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
@RequestMapping("/api/produtos")
public class Controller {

    @Autowired
    private ProdutoService service;

    ModelMapper mapper = new ModelMapper();

    @GetMapping
    public List<ProdutoResponse> obterTodos() {
        List<ProdutoDto> dto = service.obterTodos();

        return dto.stream()
        .map(p -> mapper.map(p, ProdutoResponse.class))
        .collect(Collectors.toList());
    }

    @GetMapping(value = "/obterPorCodigo/{codigo}")
    public ProdutoResponse obterPorCodigo(@PathVariable int codigo) {
        ProdutoDto dto = service.obterPorCodigo(codigo);

        return mapper.map(dto, ProdutoResponse.class);
    }

    @GetMapping(value = "/obterEstoque/{codigo}")
    public int obterEstoque(@PathVariable int codigo) {
        return service.obterQuantidadeEstoque(codigo);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> obterPorId(@PathVariable String id) {
        Optional<ProdutoDto> dto = service.obterPorId(id);

        if (dto.isPresent()) {
            ProdutoResponse response = mapper.map(dto.get(), ProdutoResponse.class);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> incluirProduto(@RequestBody @Valid ProdutoInclusao inclusao) {
        ProdutoDto dto = mapper.map(inclusao, ProdutoDto.class);
        ProdutoDto produtoInserido = service.inserirProduto(dto);

        return new ResponseEntity<>(mapper.map(produtoInserido, ProdutoResponse.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable String id, @RequestBody ProdutoAlteracao alteracao) {
        ProdutoDto dto = mapper.map(alteracao, ProdutoDto.class);
        ProdutoDto produtoAtualizado = service.atualizarProduto(id, dto);

        return new ResponseEntity<>(mapper.map(produtoAtualizado, ProdutoResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> excluirProduto(@PathVariable String id) {
        service.removerProduto(id);

        return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
    }
}
