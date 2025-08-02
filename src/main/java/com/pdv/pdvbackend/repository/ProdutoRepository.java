package com.pdv.pdvbackend.repository;

import com.pdv.pdvbackend.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    Optional<ProdutoModel> findByCodigoBarras(String codigoBarras);
}