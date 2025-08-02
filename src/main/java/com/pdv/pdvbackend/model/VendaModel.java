package com.pdv.pdvbackend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venda")
public class VendaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendaItemModel> itens;

    public VendaModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<VendaItemModel> getItens() {
        return itens;
    }

    public void setItens(List<VendaItemModel> itens) {
        this.itens = itens;
    }

    @PrePersist
    @PreUpdate
    public void calcularValorTotal() {
        if (this.itens != null && !this.itens.isEmpty()) {
            this.valorTotal = this.itens.stream()
                    .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.valorTotal = BigDecimal.ZERO;
        }
    }
}