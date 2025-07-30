package com.pdv.pdvbackend.repository;

import com.pdv.pdvbackend.model.ProdutoModel; // Importe ProdutoModel
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> { // Use ProdutoModel aqui
    // Você pode adicionar métodos de busca aqui, como findByCodigoBarras, findBySku, etc.
    // Exemplo:
    // Optional<ProdutoModel> findByCodigoBarras(String codigoBarras);
    // Optional<ProdutoModel> findBySku(String sku);
    // List<ProdutoModel> findByNomeContainingIgnoreCase(String nome);
}