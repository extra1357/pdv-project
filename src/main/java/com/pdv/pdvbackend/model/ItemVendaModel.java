package com.pdv.pdvbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_venda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Relacionamento Many-to-One com ProdutoModel
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoModel produto; // Deve ser ProdutoModel

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoTotalItem;

    @ManyToOne(fetch = FetchType.LAZY) // Relacionamento Many-to-One com VendaModel
    @JoinColumn(name = "venda_id", nullable = false)
    private VendaModel venda;
}