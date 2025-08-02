package com.pdv.pdvbackend.dto;

import com.pdv.pdvbackend.model.ProdutoModel;
import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private BigDecimal precoVenda;
    private Integer estoque;
    private String codigoBarras;
    private String sku;

    public ProdutoResponseDTO(ProdutoModel produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.precoVenda = produto.getPrecoVenda();
        this.estoque = produto.getEstoque();
        this.codigoBarras = produto.getCodigoBarras();
        this.sku = produto.getSku();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}