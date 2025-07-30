package com.pdv.pdvbackend.controller;

import com.pdv.pdvbackend.model.ProdutoModel; // ALTERADO: Importar ProdutoModel
import com.pdv.pdvbackend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Endpoint para listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarTodosProdutos() { // ALTERADO: Tipo de retorno ProdutoModel
        List<ProdutoModel> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    // Endpoint para buscar um produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscarProdutoPorId(@PathVariable Long id) { // ALTERADO: Tipo de retorno ProdutoModel
        Optional<ProdutoModel> produto = produtoService.buscarProdutoPorId(id);
        return produto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo produto
    @PostMapping
    public ResponseEntity<ProdutoModel> criarProduto(@RequestBody ProdutoModel produto) { // ALTERADO: Tipo de parâmetro e retorno ProdutoModel
        ProdutoModel novoProduto = produtoService.salvarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    // Endpoint para atualizar um produto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoModel produtoAtualizado) { // ALTERADO: Tipos de parâmetro e retorno ProdutoModel
        try {
            ProdutoModel produto = produtoService.atualizarProduto(id, produtoAtualizado);
            return ResponseEntity.ok(produto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar um produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Opcional: Endpoint para buscar produtos por termo (para a tela de vendas)
    // @GetMapping("/buscar")
    // public ResponseEntity<List<ProdutoModel>> buscarProdutos(@RequestParam String termo) {
    //     List<ProdutoModel> produtos = produtoService.buscarProdutosPorTermo(termo);
    //     return ResponseEntity.ok(produtos);
    // }
}