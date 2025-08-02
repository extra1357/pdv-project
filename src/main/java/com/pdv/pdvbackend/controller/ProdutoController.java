package com.pdv.pdvbackend.controller;

import com.pdv.pdvbackend.dto.ProdutoResponseDTO;
import com.pdv.pdvbackend.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }
    
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoResponseDTO produtoDTO) {
        ProdutoResponseDTO novoProduto = produtoService.salvarProduto(produtoDTO);
        return ResponseEntity.status(201).body(novoProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        ProdutoResponseDTO produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }
    
    @GetMapping("/codigo-barras/{codigoBarras}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorCodigoBarras(@PathVariable String codigoBarras) {
        ProdutoResponseDTO produto = produtoService.buscarPorCodigoBarras(codigoBarras);
        return ResponseEntity.ok(produto);
    }
}